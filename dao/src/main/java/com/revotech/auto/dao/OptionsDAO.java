package com.revotech.auto.dao;


import com.revotech.auto.dataSource.DataSource;
import com.revotech.auto.entity.Option;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Class {@code OptionsDAO} is a dao class which processes sql requests.
 * @author Revotech
 */
public final class OptionsDAO{

    static Logger logger = Logger.getLogger(OptionsDAO.class.getName());


    public static final String SQL_QUERY_GET_ALL_OPTIONS = "SELECT * FROM OPTIONS ";

    public static final String SQL_QUERY_GET_CUSTOMER_OPTIONS = "SELECT OPTION_ID FROM CUSTOMER_OPTIONS WHERE CUSTOMER_LOGIN = ?";

    private static final String SQL_QUERY_CUSTOMER_SELECT = "INSERT INTO CUSTOMER_OPTIONS VALUES(?,?) ";

    private static final String SQL_QUERY_DELETE_CUSTOMER_OPTIONS = "DELETE FROM CUSTOMER_OPTIONS WHERE CUSTOMER_LOGIN = ?";

    private static final String SQL_QUERY_GET_OPTIONS_BY_ID = "SELECT LABEL FROM OPTIONS WHERE ID = ?";

    private static final OptionsDAO optionsDAO = new OptionsDAO();

    /**
     * <p>This method adds selected options to a database.</p>
     * <p>
     * @param customerLogin is necessary to determine a customer who chooses this same options.
     * @param optionId is necessary to determine which options customer want to choose
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public void addSelectedOptions(String customerLogin, int optionId)  throws SQLException, IOException, PropertyVetoException{
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_CUSTOMER_SELECT)){
            logger.info("Adding option with id " + optionId + " to a customer " + customerLogin + ".");
            preparedStatement.setString(1, customerLogin);
            preparedStatement.setInt(2, optionId);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * <p>This method returns label of the particular option.</p>
     * @param id is necessary to determine the label of the particular option
     * @return label of the particular option
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public String getOptionByID(int id) throws SQLException, IOException, PropertyVetoException {
        String label = null;
        ResultSet resultSet = null;
            try (Connection connection = DataSource.getInstance().getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_OPTIONS_BY_ID)){
                logger.info("Getting option by id: " + id);
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    label = resultSet.getString(1);
                }
        } finally{
                if(resultSet != null)
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        logger.error("Error while closing result set.\n"  + e );
                    }
            }
        return label;
    }

    /**
     * <p>This method gets the list of all options.</p>
     * <p>
     * @return list of all options.
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public ArrayList<Option> getOptions() throws SQLException, IOException, PropertyVetoException {
        ArrayList<Option> options = null;
        try(Connection connection = DataSource.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_QUERY_GET_ALL_OPTIONS)) {
            options = initOptions(resultSet);
            logger.info("Got these options: " + options);
        }
        return options;
    }


    /**
     * <p>This method initializes options.</p>
     * <p>
     * @param resultSet is necessary to divide response.
     * @return initialized list of all options.
     * @throws SQLException
     */
    private ArrayList<Option> initOptions(ResultSet resultSet) throws SQLException {
        ArrayList<Option> options = new ArrayList<>();
        while (resultSet.next()) {
            logger.info("Initializing option.");
            Option option = new Option();
            option.setLabel(resultSet.getString(1));
            option.setId(resultSet.getInt(2));
            options.add(option);
        }
        return options;
    }

    /**
     * <p>This method deletes options from a database.</p>
     * <p>
     * @param login is necessary to determine customer who has chosen this same options.
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public void deleteOptions(String login) throws SQLException, IOException, PropertyVetoException {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_DELETE_CUSTOMER_OPTIONS)){
            logger.info("Deleting options belonging to a customer: " + login);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * <p>This method gets options selected by particular customer.</p>
     * @param login is necessary to determine particular customer who has chosen this same options.
     * @return list of checked options.
     * @throws PropertyVetoException
     * @throws SQLException
     * @throws IOException
     */
    public ArrayList<Integer> getCheckedOptions(String login) throws PropertyVetoException, SQLException, IOException {
        ArrayList<Integer> options = new ArrayList<>();
        ResultSet resultSet = null;
        try( Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_CUSTOMER_OPTIONS)){
            logger.info("Getting checked options belonging to customer: " + login);
            preparedStatement.setString(1, login);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                options.add(resultSet.getInt(1));
            }
        }finally{
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("Error while closing result set.\n" + e);
                }
        }
        return options;
    }

    public static OptionsDAO getInstance(){
        return optionsDAO;
    }

}

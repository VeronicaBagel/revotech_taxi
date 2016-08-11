package com.revotech.auto.dao;

import com.revotech.auto.dataSource.DataSource;
import com.revotech.auto.entity.Customer;
import org.apache.log4j.Logger;


import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;

/**
 * Class {@code CustomerDAO} is a dao class which processes sql requests.
 * @author Revotech
 */
public final class CustomerDAO {

    static Logger logger = Logger.getLogger(CustomerDAO.class.getName());

    private static final String SQL_QUERY_ADD_CUSTOMER= "INSERT INTO CUSTOMERS (LOGIN, PASSWORD, EMAIL) VALUES (?,?,?)";

    private static final String SQL_QUERY_GET_CUSTOMER= "SELECT * FROM CUSTOMERS WHERE LOGIN= ?";

    private static final CustomerDAO customerDAO = new CustomerDAO();

    /**
     * <p>This method adds customer to a database.</p>
     * <p>
     * @param customer is necessary to add customer info into database.
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public void addCustomer(Customer customer) throws SQLException, IOException, PropertyVetoException {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_CUSTOMER)
            )
            {
                logger.info("Adding this customer to a database: " + customer);
                preparedStatement.setString(1, customer.getLogin());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.setString(3, customer.getEmail());
                preparedStatement.executeUpdate();
            }
    }

    /**
     * <p>This method get customer with certain login.</p>
     * <p>
     * @param enterLogin is necessary to determine particular customer.
     * @return particular customer
     */
    public Customer getCustomer(String enterLogin) {
        Customer customer = null;
        ResultSet resultSet = null;
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_CUSTOMER)) {
            preparedStatement.setString(1, enterLogin);
            resultSet = preparedStatement.executeQuery();
            customer = initCustomer(resultSet);
            logger.info("Got customer " + customer + " from a database.");
        } catch (IOException | PropertyVetoException | SQLException e) {
            logger.error("SQL, IOE or PropertyVetoException occurred during adding news");
        }finally{
            if(resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Error while closing result set.\n" + e);
                e.printStackTrace();
            }
        }
        return customer;
    }

    /**
     * <p>This method initializes customer.</p>
     * <p>
     * @param resultSet is necessary to divide response.
     * @return initialized customer
     * @throws SQLException
     */
    private Customer initCustomer(ResultSet resultSet) throws SQLException {
        Customer customer = null;
        while(resultSet.next()) {
            customer = new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return customer;
    }

    public static  CustomerDAO getInstance(){
        return customerDAO;
    }
}

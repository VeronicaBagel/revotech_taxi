package com.revotech.auto;

import com.revotech.auto.dao.FactoryDAO;
import com.revotech.auto.dao.OptionsDAO;
import com.revotech.auto.entity.Option;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class {@code OptionService} is a service class provides connection between web and dao modules for option entity.
 * @author Revotech
 */
public class OptionService{
    private FactoryDAO factoryDAO = FactoryDAO.getFactory();
    private OptionsDAO dao = factoryDAO.getOptionsDAO();
    private static OptionService optionService;

    private OptionService(){}
    /**
     * <p>This method calls options dao to get option label by it's id.</p>
     * <p>
     * @param id is necessary to determine particular option id
     * @return options label
     * @throws PropertyVetoException
     * @throws IOException
     * @throws SQLException
     */
    public String getOptionByID(int id) throws PropertyVetoException, IOException, SQLException {
        return dao.getOptionByID(id);
    }

    /**
     * <p>This method calls options dao to add selected options to a database.</p>
     * <p>
     * @param customerLogin is necessary to determine customer which has chosen this same options.
     * @param optionId is necessary to determine chosen options.
     * @throws SQLException
     * @throws PropertyVetoException
     * @throws IOException
     */
    public void addSelectedOptions(String customerLogin, int optionId) throws SQLException, PropertyVetoException, IOException{
        dao.addSelectedOptions(customerLogin, optionId);
    }

    /**
     * <p>This method calls options dao to delete particular options from a database.</p>
     * @param login is necessary to determine the particular customer.
     * @throws PropertyVetoException
     * @throws SQLException
     * @throws IOException
     */
    public void deleteOptions (String login) throws PropertyVetoException, SQLException, IOException {
        dao.deleteOptions(login);
    }

    /**
     * <p>This method calls options dao to get the list of all options.</p>
     * <p>
     * @return options list
     * @throws PropertyVetoException
     * @throws SQLException
     * @throws IOException
     */
    public ArrayList<Option> getOptions() throws PropertyVetoException, SQLException, IOException {
       return dao.getOptions();
    }

    /**
     * <p>This method calls options dao to get checked options from a database.</p>
     * @param login is necessary to determine the customer which has chosen this same options.
     * @return list of checked options
     * @throws PropertyVetoException
     * @throws IOException
     * @throws SQLException
     */
    public ArrayList<Integer> getCheckedOptions(String login) throws PropertyVetoException, IOException, SQLException {
        return dao.getCheckedOptions(login);
    }

    public static OptionService getInstance() {
        if (optionService == null) {
            synchronized(OptionService.class) {
                if (optionService == null) {
                    optionService = new OptionService();
                }
            }
        }
        return optionService;
    }
}

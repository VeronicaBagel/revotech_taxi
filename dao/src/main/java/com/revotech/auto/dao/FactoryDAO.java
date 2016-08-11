package com.revotech.auto.dao;

/**
 * Class {@code FactoryDAO} is the class which returns necessary dao objects.
 * @author Revotech
 */
public final class FactoryDAO {
    private static final FactoryDAO factoryDAO = new FactoryDAO();

    public static FactoryDAO getFactory(){
        return factoryDAO;
    }

    public OptionsDAO getOptionsDAO(){
        return OptionsDAO.getInstance();
    }

    public CustomerDAO getCustomerDAO() {
        return CustomerDAO.getInstance();
    }
}

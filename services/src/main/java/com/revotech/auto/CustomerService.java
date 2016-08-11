package com.revotech.auto;

import com.revotech.auto.dao.CustomerDAO;
import com.revotech.auto.dao.FactoryDAO;
import com.revotech.auto.entity.Customer;
import com.revotech.auto.exception.CustomerNotFoundException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class {@code CustomerService} is a service class provides connection between web and dao modules for customer entity.
 * @author Revotech
 */
public class CustomerService{

    private FactoryDAO factoryDAO = FactoryDAO.getFactory();
    private CustomerDAO dao = factoryDAO.getCustomerDAO();
    private static CustomerService customerService;

    private CustomerService(){}
    /**
     * <p>This method calls customer dao to add customer to a database.</p>
     * <p>
     * @param customer is necessary ti add customer info into a database.
     * @throws SQLException
     * @throws IOException
     * @throws PropertyVetoException
     */
    public void addCustomer(Customer customer) throws SQLException, IOException, PropertyVetoException {
        dao.addCustomer(customer);
    }


    /**
     * <p>This method calls customer dao to return customer.</p>
     * <p>
     * @param customerLogin is necessary to determine a customer.
     * @return found customer of null if customer not found.
     */
    public Customer getCustomer(String customerLogin) throws CustomerNotFoundException {
        Customer customer =(Customer)dao.getCustomer(customerLogin);
        if (customer!=null){
            return customer;
        }
        else {
            throw new CustomerNotFoundException();
        }
    }

    public static CustomerService getInstance() {
        if (customerService == null) {
            synchronized(CustomerService.class)
            {
                if (customerService == null) {
                    customerService = new CustomerService();
                }
            }
        }
        return customerService;

    }


}

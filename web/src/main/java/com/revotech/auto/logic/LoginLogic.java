package com.revotech.auto.logic;

import com.revotech.auto.CustomerService;
import com.revotech.auto.entity.Customer;
import com.revotech.auto.exception.CustomerNotFoundException;
import org.apache.log4j.Logger;

/**
 * Class {@code LoginLogic} is the class which deals with checking customers's input.
 * @author Revotech
 */
public final class LoginLogic {
    private static final Logger logger = Logger.getLogger(LoginLogic.class.getName());

    /**
     * <p>This method checks if there's a customer with such login and password.</p>
     * <p>
     * @param enterLogin is necessary to determine a particular customer.
     * @param enterPassword is necessary to determine a particular customer.
     * @return true if there's such customer and false otherwise.
     */
    public static boolean checkCustomer(String enterLogin, String enterPassword) throws CustomerNotFoundException {
        logger.info("Calling customer service to get customer " + enterLogin + " with password " + enterPassword);
        Customer customer = CustomerService.getInstance().getCustomer(enterLogin);
        if(customer.getPassword().equals(enterPassword) ){
            return true;
        }
        return false;
    }

}

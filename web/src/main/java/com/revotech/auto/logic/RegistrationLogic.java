package com.revotech.auto.logic;

import com.revotech.auto.CustomerService;
import com.revotech.auto.entity.Customer;
import com.revotech.auto.exception.CustomerNotFoundException;
import org.apache.log4j.Logger;

/**
 * Class {@code RegistrationLogic} is the class which deals with checking customers's input.
 * @author Revotech
 */
public final class RegistrationLogic {
    private static final Logger logger = Logger.getLogger(RegistrationLogic.class.getName());

    /**
     * <p>This method checks if there's already a customer with such login.</p>
     * <p>
     * @param enterLogin is necessary if such customer already exists.
     * @return true if there's no such customer yet and false otherwise.
     */
    public static boolean isLoginDistinct(String enterLogin) {
        logger.info("Calling customer service to determine whether a customer" + enterLogin +
                " already exist. ");
        try {
            Customer customer = CustomerService.getInstance().getCustomer(enterLogin);
        } catch (CustomerNotFoundException e) {
            return true;
        }
        return false;
    }



}

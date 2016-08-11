package com.revotech.auto.command.basicCommand;

import com.revotech.auto.CustomerService;
import com.revotech.auto.command.ICommand;
import com.revotech.auto.constants.AttributeConst;
import com.revotech.auto.entity.Customer;
import com.revotech.auto.logic.OptionsLogic;
import com.revotech.auto.logic.RegistrationLogic;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class {@code RegistrationCommand} is the class which which implements {@code ICommand} interface and
 * deals with registering customer.
 * @author Revotech
 */
public final class RegistrationCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class.getName());
    private static RegistrationCommand registrationCommand;

    private RegistrationCommand(){}
    /**
     * <p>This method gets parameters from a jsp page, calls {@code RegistrationLogic}
     * (which checks customer input) and then forwards customer to a certain page.</p>
     * <p>
     * @param request is necessary for getting parameters and forwarding to a page.
     * @param response is necessary for forwarding customer to a certain page
     * @see SQLException
     * @see PropertyVetoException
     * @see IOException
     * @see ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        ResourceBundle bundle_page = ResourceBundle.getBundle("page_titles");
        String enterLogin = request.getParameter(AttributeConst.ATTR_USERNAME);
        String enterEmail = request.getParameter(AttributeConst.ATTR_EMAIL);
        String enterPass = request.getParameter(AttributeConst.ATTR_PASSWORD);

        Customer customer = new Customer(enterLogin, enterPass, enterEmail);

        logger.info("Setting customer: " + customer);



        try {
            if (RegistrationLogic.isLoginDistinct(enterLogin)) {
                CustomerService.getInstance().addCustomer(customer);
                logger.info("Setting login to a session: " + enterLogin);
                request.getSession().setAttribute(AttributeConst.ATTR_USER, customer.getLogin());
                OptionsLogic.setAllOptions(request);
                logger.info("Forwarding customer to "+ bundle_page.getString("page_title.options"));
                request.getServletContext().getRequestDispatcher(bundle_page.getString("page_title.options")).forward(request,response);
            } else {
                logger.info("Setting error message and forwarding customer to " + bundle_page.getString("page_title.registration"));
                request.setAttribute(AttributeConst.ERROR_MESSAGE, "locale.error_not_distinct_field");
                request.getServletContext().getRequestDispatcher(bundle_page.getString("page_title.registration")).forward(request,response);

            }
        } catch (SQLException | PropertyVetoException | IOException | ServletException e) {
            logger.error("SQL, IO, Servlet or PropertyVeto Exception occurred while processing RegistrationCommand\n" + e);
        }

    }

    public static RegistrationCommand getInstance(){
        if(registrationCommand == null){
            synchronized (RegistrationCommand.class){
                if(registrationCommand == null){
                    registrationCommand = new RegistrationCommand();
                }
            }
        }
        return registrationCommand;
    }
}

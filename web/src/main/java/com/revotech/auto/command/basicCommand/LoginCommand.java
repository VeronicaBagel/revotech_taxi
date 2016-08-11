package com.revotech.auto.command.basicCommand;

import com.revotech.auto.command.ICommand;
import com.revotech.auto.constants.AttributeConst;
import com.revotech.auto.exception.CustomerNotFoundException;
import com.revotech.auto.logic.LoginLogic;
import com.revotech.auto.logic.OptionsLogic;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class {@code LoginCommand} is the class which which implements {@code ICommand} interface and
 * deals with logging customer in.
 * @author Revotech
 */
public final class LoginCommand implements ICommand {
    private static Logger logger = Logger.getLogger(LoginCommand.class.getName());
    private static LoginCommand loginCommand;

    private LoginCommand(){}
    /**
     * <p>This method gets parameters from a jsp page, calls {@code LoginLogic}
     * (which checks customer input) and then forwards customer to a certain page.</p>
     * <p>
     * @param request is necessary for getting parameters and forwarding to a page.
     * @param response is necessary for forwarding customer to a certain page.
     * @see ServletException
     * @see IOException
     */
   @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
       ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        String login = request.getParameter(AttributeConst.ATTR_USERNAME);
        String pass = request.getParameter(AttributeConst.ATTR_PASSWORD);
        logger.info("Getting login (" + login + ") and password (" + pass + ") from a jsp page.");
            try {
                if(LoginLogic.checkCustomer(login, pass)) {
                    logger.info("Session login: " + login);
                    request.getSession().setAttribute(AttributeConst.ATTR_USER, login);
                    OptionsLogic.setAllOptions(request);
                    OptionsLogic.setCheckedOptions(request, login);
                    logger.info("Forwarding customer to a " + bundle.getString("page_title.options") );
                    request.getServletContext().getRequestDispatcher(bundle.getString("page_title.options")).forward(request, response);
                }else {
                    request.setAttribute(AttributeConst.ERROR_MESSAGE, "locale.error_invalid_customer");
                    logger.info("Forwarding customer to a " + bundle.getString("page_title.login")  );
                    request.getServletContext().getRequestDispatcher(bundle.getString("page_title.login")).forward(request, response);
                }
            } catch (SQLException | ServletException | IOException | PropertyVetoException e) {
                logger.error("SQL, IO, Servlet or PropertyVeto Exception occurred while processing LoginCommand\n" + e);
            } catch (CustomerNotFoundException e) {
                request.getSession().setAttribute(AttributeConst.ERROR_MESSAGE, "locale.error_no_such_customer");
                logger.error("No customer with login " + login);
            }
   }

    public static LoginCommand getInstance(){
        if(loginCommand == null){
            synchronized (LoginCommand.class){
                if(loginCommand == null){
                    loginCommand = new LoginCommand();
                }
            }
        }
        return loginCommand;
    }

}

package com.revotech.auto.command.pageCommand;

import com.revotech.auto.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class {@code RegistrationPageCommand} is the class which which implements {@code ICommand} interface and
 * deals with forwarding customer to a page.
 * @author Revotech
 */
public final class RegistrationPageCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(RegistrationPageCommand.class.getName());
    private static RegistrationPageCommand registrationPageCommand;

    private RegistrationPageCommand(){}

    /**
     * <p>This method forwards customer to a certain page.</p>
     * <p>
     * @param request is necessary for forwarding customer to a certain page.
     * @param response is necessary for forwarding customer to a certain page.
     * @see ServletException
     * @see IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        try {
            logger.info("Forwarding customer to a " + bundle.getString("page_title.registration"));
            request.getServletContext().getRequestDispatcher(bundle.getString("page_title.registration")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("IO or Servlet Exception occurred while processing RegistrationPageCommand\n" + e);
        }
    }

    public static RegistrationPageCommand getInstance(){
        if(registrationPageCommand == null){
            synchronized (RegistrationPageCommand.class){
                if(registrationPageCommand == null){
                    registrationPageCommand = new RegistrationPageCommand();
                }
            }
        }
        return registrationPageCommand;
    }
}

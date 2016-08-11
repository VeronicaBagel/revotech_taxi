package com.revotech.auto.command.pageCommand;

import com.revotech.auto.command.ICommand;
import com.revotech.auto.command.basicCommand.LoginCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class {@code LoginPageCommand} is the class which which implements {@code ICommand} interface and
 * deals with forwarding customer to a page.
 * @author Revotech
 */
public final class LoginPageCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(LoginPageCommand.class.getName());
    private static LoginPageCommand loginPageCommand;

    private LoginPageCommand(){}
    /**
     * <p>This method forwards customer to a certain page.</p>
     * <p>
     * @param request is necessary for forwarding customer to a certain page.
     * @param response is necessary for forwarding customer to a certain page.
     * @see ServletException
     * @see IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        try {
            logger.info("Forwarding customer to " +  bundle.getString("page_title.login"));
            request.getServletContext().getRequestDispatcher(bundle.getString("page_title.login")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("IO or Servlet Exception occurred while processing LoginPageCommand\n" + e);
        }
    }

    public static LoginPageCommand getInstance(){
        if(loginPageCommand == null){
            synchronized (LoginCommand.class){
                if(loginPageCommand == null){
                    loginPageCommand = new LoginPageCommand();
                }
            }
        }
        return loginPageCommand;
    }
}

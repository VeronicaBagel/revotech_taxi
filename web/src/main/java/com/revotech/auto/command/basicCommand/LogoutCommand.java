package com.revotech.auto.command.basicCommand;

import com.revotech.auto.command.ICommand;
import com.revotech.auto.constants.AttributeConst;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class {@code LogoutCommand} is the class which which implements {@code ICommand} interface and
 * deals with logging customer out.
 * @author Revotech
 */
public final class LogoutCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(LogoutCommand.class.getName());
    private static LogoutCommand logoutCommand;

    private LogoutCommand(){}
    /**
     * <p>This method invalidates session and forwards customer to a page.</p>
     * <p>
     * @param request is necessary for forwarding customer to a certain page
     * @param response is necessary for forwarding customer to a certain page
     * @see ServletException
     * @see IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        logger.info("Invalidate session.");
        request.getSession().invalidate();
        request.setAttribute(AttributeConst.ATTR_USER, "");
        try {
            logger.info("Forwarding customer to a " + bundle.getString("page_title.login"));
            request.getServletContext().getRequestDispatcher(bundle.getString("page_title.login")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("IO or Servlet Exception occurred while processing LogoutCommand\n" + e);
        }
    }

    public static LogoutCommand getInstance(){
        if(logoutCommand == null){
            synchronized (LogoutCommand.class){
                if(logoutCommand == null){
                    logoutCommand = new LogoutCommand();
                }
            }
        }
        return logoutCommand;
    }
}

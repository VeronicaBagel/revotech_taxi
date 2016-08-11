package com.revotech.auto.command.basicCommand;

import com.revotech.auto.command.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Class {@code EmptyCommand} is the class which which implements {@code ICommand} interface and
 * deals with processing empty action.
 * @author Revotech
 */
public final class EmptyCommand implements ICommand {
    private static Logger logger = Logger.getLogger(EmptyCommand.class.getName());
    private static EmptyCommand emptyCommand;

    private EmptyCommand(){}

    /**
     * <p>This method forwards customer to a certain page.</p>
     * <p>
     * @param request is necessary for forwarding customer to a certain page.
     * @param response is necessary for forwarding customer to a certain page.
     * @see ServletException
     * @see IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        try {
            logger.info("Forwarding customer to a " + bundle.getString("page_title.login"));
            request.getServletContext().getRequestDispatcher(bundle.getString("page_title.login")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("IO or Servlet Exception occurred while processing EmptyCommand\n" + e);
        }
    }

    public static EmptyCommand getInstance(){
        if(emptyCommand == null){
            synchronized (EmptyCommand.class){
                if(emptyCommand == null){
                    emptyCommand = new EmptyCommand();
                }
            }
        }
        return emptyCommand;
    }
}

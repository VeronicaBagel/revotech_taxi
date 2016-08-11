package com.revotech.auto.command.basicCommand;

import com.revotech.auto.OptionService;
import com.revotech.auto.command.ICommand;
import com.revotech.auto.constants.AttributeConst;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Class {@code ChooseFunctionsCommand} is the class which implements {@code ICommand} interface
 * and deals with processing functions chosen by customer.
 * @author Revotech
 */
public final class ChooseFunctionsCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(ChooseFunctionsCommand.class.getName());
    private static ChooseFunctionsCommand chooseFunctionsCommand;

    private ChooseFunctionsCommand(){}
    /**
     * <p>This method gets chosen options from a jsp page, calls services to
     * add them to a database and forwards customer to a particular page.</p>
     * <p>
     * @param request is necessary for getting parameters and forwarding.
     * @param response is necessary for forwarding.
     * @see SQLException
     * @see ServletException
     * @see IOException
     * @see PropertyVetoException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response){
        ResourceBundle bundle = ResourceBundle.getBundle("page_titles");
        String[] functions = request.getParameterValues(AttributeConst.ATTR_OPTION);
        logger.info("Getting chosen option ids: " + functions.toString());
        ArrayList<String> optionsLabels = new ArrayList<>();
        try {
            OptionService.getInstance().deleteOptions((String)request.getSession().getAttribute(AttributeConst.ATTR_USER));
            if (functions != null) {
                for (String function : functions) {
                    optionsLabels.add(OptionService.getInstance().getOptionByID(Integer.parseInt(function)));
                    OptionService.getInstance().addSelectedOptions((String) request.getSession().getAttribute(AttributeConst.ATTR_USER), Integer.parseInt(function));
                }
                logger.info("Setting session attribute for functions to: " + optionsLabels.toString());
                request.getSession().setAttribute(AttributeConst.FUNCTIONS_LABEL, optionsLabels);
            } else{
                logger.info("Setting empty session attribute.");
                request.getSession().setAttribute(AttributeConst.FUNCTIONS_LABEL, "");
            }
            logger.info("Forwarding customer to a " + bundle.getString("page_title.functions"));
            request.getServletContext().getRequestDispatcher(bundle.getString("page_title.functions")).forward(request, response);
        } catch (SQLException | ServletException | IOException | PropertyVetoException e) {
            logger.error("SQL, IO, Servlet or PropertyVeto Exception occurred while processing ChooseFunctionsCommand\n" + e);
        }
    }

    public static ChooseFunctionsCommand getInstance(){
        if(chooseFunctionsCommand == null){
            synchronized (ChooseFunctionsCommand.class){
                if(chooseFunctionsCommand == null){
                    chooseFunctionsCommand = new ChooseFunctionsCommand();
                }
            }
        }
        return chooseFunctionsCommand;
    }
}

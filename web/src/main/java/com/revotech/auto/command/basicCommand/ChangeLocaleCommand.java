package com.revotech.auto.command.basicCommand;


import com.revotech.auto.OptionService;
import com.revotech.auto.command.ICommand;
import com.revotech.auto.constants.AttributeConst;
import com.revotech.auto.logic.OptionsLogic;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class {@code ChangeLocaleCommand} is the class which implements {@code ICommand} interface
 * and deals with changing the locale.
 * @author Revotech
 */

public final class ChangeLocaleCommand implements ICommand {
    private static final Logger logger = Logger.getLogger(ChangeLocaleCommand.class.getName());
    private static ChangeLocaleCommand changeLocaleCommand;

    private ChangeLocaleCommand(){}

    /**
     * <p>This method gets current locale ang changes it.</p>
     * <p>
     * @param request is necessary for getting parameters and forwarding to a page.
     * @param response is necessary for forwarding customer to a certain page.
     * @see ServletException
     * @see IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String locale = request.getParameter(AttributeConst.ATTR_LOCALE).toLowerCase();
            String login = (String) request.getSession().getAttribute(AttributeConst.ATTR_USER);
            logger.info("Setting proper locale: " + locale);
            request.getSession().setAttribute(AttributeConst.ATTR_LOCALE, locale);

           if (Objects.equals(request.getParameter(AttributeConst.ATTR_CUSTOMER_OPTIONS), "true")) {
                ArrayList<Integer> functionsId = OptionService.getInstance().getCheckedOptions(login);
                ArrayList<String> labels = new ArrayList<>();
                for( Integer id : functionsId ){
                    labels.add(OptionService.getInstance().getOptionByID(id));
                }
                request.getSession().setAttribute(AttributeConst.FUNCTIONS_LABEL, labels);
            }

            OptionsLogic.setAllOptions(request);
            OptionsLogic.setCheckedOptions(request,login);
            String page = request.getParameter(AttributeConst.ATTR_PAGE);
            request.getServletContext().getRequestDispatcher(page).forward(request, response);
            logger.info("Locale is successfully set.");

        } catch (ServletException | IOException | SQLException | PropertyVetoException  e){
            logger.error("SQL, IO, Servlet or PropertyVeto Exception occurred while changing locale.");
        }
    }

    public static ChangeLocaleCommand getInstance(){
        if(changeLocaleCommand == null){
            synchronized (ChangeLocaleCommand.class){
                if(changeLocaleCommand == null){
                    changeLocaleCommand = new ChangeLocaleCommand();
                }
            }
        }
        return changeLocaleCommand;
    }
}
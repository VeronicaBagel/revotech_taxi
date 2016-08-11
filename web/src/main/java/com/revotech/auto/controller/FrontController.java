package com.revotech.auto.controller;

import com.revotech.auto.command.*;
import com.revotech.auto.command.basicCommand.*;
import com.revotech.auto.command.pageCommand.LoginPageCommand;
import com.revotech.auto.command.pageCommand.RegistrationPageCommand;
import com.revotech.auto.constants.AttributeConst;
import com.revotech.auto.constants.CommandConst;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Class {@code FrontController} is the class which which extends {@code HttpServlet} class and
 * deals with forwarding customer to a page.processing different customer actions.
 * @author Revotech
 */
@WebServlet("/controller")
public final class FrontController extends HttpServlet {
    static Logger logger = Logger.getLogger(FrontController.class.getName());

    /**
     * <p>Starts when servlet is processing GET request.</p>
     * <p>
     * @param request is the servlet parameter.
     * @param response is the servlet parameter.
     * @throws ServletException
     * @throws IOException
     * @see SQLException
     * @see ServletException
     * @see IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * <p>Starts when servlet is processing POST request.</p>
     * <p>
     * @param request is the servlet parameter.
     * @param response is the servlet parameter.
     * @throws ServletException
     * @throws IOException
     * @see SQLException
     * @see ServletException
     * @see IOException
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Processes each and every action provided for user to perform.</p>
     * <p>
     * @param request is the servlet parameter.
     * @param response is the servlet parameter.
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * @see SQLException
     * @see ServletException
     * @see IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        logger.info("FrontController used.");
        String encoding = request.getCharacterEncoding();
        if ((encoding != null) && (encoding.equalsIgnoreCase("utf-8")))
        {
            response.setContentType("text/html; charset=utf-8");
        }
        String action = request.getParameter(AttributeConst.ATTR_COMMAND);
        logger.info(" ======== Starting command: " + action + " ======== ");
        ICommand command = null;
        switch (action) {
            case CommandConst.REGISTER_PAGE:
                command = RegistrationPageCommand.getInstance();
                break;
            case CommandConst.LOGOUT:
                command = LogoutCommand.getInstance();
                break;
            case CommandConst.CHOOSE_FUNCTIONS:
                command = ChooseFunctionsCommand.getInstance();
                break;
            case CommandConst.LOGIN_PAGE:
                command = LoginPageCommand.getInstance();
                break;
            case CommandConst.LOGIN:
                command = LoginCommand.getInstance();
                break;
            case CommandConst.REGISTRATION:
                command = RegistrationCommand.getInstance();
                break;
            case CommandConst.CHANGING_LOCALE:
                command = ChangeLocaleCommand.getInstance();
                break;
            default:
                command = EmptyCommand.getInstance();
                break;
        }
        command.execute(request, response);
    }
}

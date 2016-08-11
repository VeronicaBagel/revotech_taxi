package com.revotech.auto.logic;

import com.revotech.auto.OptionService;
import com.revotech.auto.constants.AttributeConst;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class {@code OptionsLogic} is the class which deals with
 * getting all available options.
 * @author Revotech
 */

public final class OptionsLogic {
    private static final Logger logger = Logger.getLogger(OptionsLogic.class.getName());

    /**
     * <p>This method sets necessary session attributes.</p>
     * <p>
     * @param request is necessary for setting proper attributes.
     * @throws PropertyVetoException
     * @throws IOException
     * @throws SQLException
     */
    public static void setAllOptions(HttpServletRequest request) throws PropertyVetoException, IOException, SQLException {
        logger.info("Setting proper session attribute for all options.");
        request.getSession().setAttribute(AttributeConst.ATTR_OPTIONS, OptionService.getInstance().getOptions());
    }

    /**
     * <p>This method sets necessary session attributes.</p>
     * <p>
     * @param request is necessary for setting proper attributes.
     * @param login is necessary for getting options which were checked by a particular customer.
     * @throws PropertyVetoException
     * @throws IOException
     * @throws SQLException
     */
    public static void setCheckedOptions(HttpServletRequest request, String login) throws PropertyVetoException, IOException, SQLException {
        ArrayList<Integer> checked_id =  OptionService.getInstance().getCheckedOptions(login);
        logger.info("Setting proper session attribute for checked options.");
        request.getSession().setAttribute(AttributeConst.ATTR_CHECKED_OPTIONS, checked_id);
    }

}

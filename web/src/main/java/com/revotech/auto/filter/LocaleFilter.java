package com.revotech.auto.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Class {@code LocaleFilter} is the class which implements {@code Filter} interface and
 * deals with changing the locale.
 * @author Revotech
 */
public final class LocaleFilter implements Filter {

    private static final Logger logger = Logger.getLogger(LocaleFilter.class.getName());

    private static final String ATTR_LOCALE = "locale";
    private static final String EN_LOCALE = "en";
    private static final String DE_LOCALE = "de";
    private String locale;


    /**
     * <p>Sets initial locale for the system.</p>
     * <p>
     * @param filterConfig is the configuration of the filter.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        locale = EN_LOCALE;
        logger.info("LocaleFilter is initialized.");
    }

    /**
     * <p>Sets necessary locale for the next pages.</p>
     * <p>
     * @param request is necessary to get actual locale and set next one.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute(ATTR_LOCALE) == null) {
            req.getSession().setAttribute(ATTR_LOCALE, locale);
        }
        if (req.getSession().getAttribute(ATTR_LOCALE) == EN_LOCALE) {
            req.getSession().setAttribute(ATTR_LOCALE, EN_LOCALE);
        }
        if (req.getSession().getAttribute(ATTR_LOCALE) == DE_LOCALE) {
            req.getSession().setAttribute(ATTR_LOCALE, DE_LOCALE);
        }
        logger.info("LocaleFilter doFilter. " + ATTR_LOCALE);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

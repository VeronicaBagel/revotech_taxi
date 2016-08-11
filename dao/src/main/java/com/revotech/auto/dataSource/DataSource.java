package com.revotech.auto.dataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code DataSource} processes connection to a database.
 * @author Revotech.
 */
public final class DataSource {
    private static DataSource datasource;
    private ComboPooledDataSource cpds;

    private static final Logger logger = Logger.getLogger(DataSource.class.getName());

    /**
     * <p>This method sets necessary properties.</p>
     * <p>
     * @throws IOException
     * @throws SQLException
     * @throws PropertyVetoException
     */
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        Locale.setDefault(Locale.ENGLISH);
        logger.info("Setting parameters for a connection.");
        ResourceBundle bundle = ResourceBundle.getBundle("db");
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(bundle.getString("SQL_DB_DRIVER_CLASS"));
        cpds.setJdbcUrl(bundle.getString("SQL_DB_URL"));
        cpds.setUser(bundle.getString("SQL_DB_USERNAME"));
        cpds.setPassword(bundle.getString("SQL_DB_PASSWORD"));
        cpds.setMinPoolSize(Integer.parseInt(bundle.getString("SQL_MIN_POOL_SIZE")));
        cpds.setAcquireIncrement(Integer.parseInt(bundle.getString("SQL_ACQUIRE_INCREMENT")));
        cpds.setMaxPoolSize(Integer.parseInt(bundle.getString("SQL_MAX_POOL_SIZE")));
        cpds.setMaxStatements(Integer.parseInt(bundle.getString("SQL_MAX_STATEMENTS")));

    }


    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            synchronized (DataSource.class) {
                if (datasource == null)
                    datasource = new DataSource();
            }
        }
        return datasource;
    }

    /**
     * <p>This method returns connection to a database.</p>
     * @return connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        logger.info("Connecting to a database.");
        return this.cpds.getConnection();
    }
}

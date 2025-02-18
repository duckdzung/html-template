package com.crud.utils;

import com.crud.constants.DatabaseConstants;
import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseUtils {
    private static DataSource dataSource;
    
    static {
        try {
            initializeDataSource();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database", e);
        }
    }
    
    private static void initializeDataSource() {
        Properties props = loadDatabaseProperties();
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(props.getProperty(DatabaseConstants.DB_URL));
        config.setUsername(props.getProperty(DatabaseConstants.DB_USERNAME));
        config.setPassword(props.getProperty(DatabaseConstants.DB_PASSWORD));
        config.setDriverClassName(props.getProperty(DatabaseConstants.DB_DRIVER));
        config.setMaximumPoolSize(Integer.parseInt(props.getProperty(DatabaseConstants.DB_POOL_SIZE)));
        
        dataSource = new HikariDataSource(config);
        initializeDatabase();
    }
    
    private static Properties loadDatabaseProperties() {
        Properties props = new Properties();
        try (var is = DatabaseUtils.class.getClassLoader().getResourceAsStream("database.properties")) {
            props.load(is);
            return props;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load database properties", e);
        }
    }
    
    private static void initializeDatabase() {
        Properties sqlProps = loadSqlProperties();
        try (Connection conn = getConnection()) {
            // Check if tables exist
            DatabaseMetaData meta = conn.getMetaData();
            ResultSet tables = meta.getTables(null, null, "users", null);
            
            if (!tables.next()) {
                // Create tables and insert default data
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(sqlProps.getProperty("sql.create.user.table"));
                    stmt.execute(sqlProps.getProperty("sql.create.customer.table"));
                    stmt.execute(sqlProps.getProperty("sql.insert.default.user"));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize database tables", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

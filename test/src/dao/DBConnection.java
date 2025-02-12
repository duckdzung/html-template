package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("config/dbconfig.properties")) {
            Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                dbUrl = prop.getProperty("db.url");
                dbUser = prop.getProperty("db.user");
                dbPassword = prop.getProperty("db.password");
            } else {
                throw new RuntimeException("Không tìm thấy file dbconfig.properties");
            }
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tải cấu hình database: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi kết nối database: " + e.getMessage());
        }
    }
}

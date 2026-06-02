package org.scoula.jdbc_ex.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {

    static Connection con = null;

    static {
        try {
            Properties properties = new Properties();
            properties.load(JDBCUtil.class.getResourceAsStream("/application.properties"));
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String id = properties.getProperty("id");
            String password = properties.getProperty("password");
            Class.forName(driver);
            con = DriverManager.getConnection(url, id, password);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("exception");
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void close() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            con = null;
        }
    }
}

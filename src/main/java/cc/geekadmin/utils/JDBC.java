package cc.geekadmin.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @Author Admin
 * @create 2022/12/24 17:29
 */
public class JDBC {

    public static Connection getConnection() {
        try {
            ResourceBundle resource = ResourceBundle.getBundle("jdbc");
            Class.forName(resource.getString("jdbc.driver"));
            String url = resource.getString("jdbc.url") ;
            String username = resource.getString("jdbc.username") ;
            String password = resource.getString("jdbc.password") ;
            Properties props = new Properties();
            props.setProperty("user", username);
            props.setProperty("password", password);
            props.setProperty("remarks", "true");
            props.setProperty("useInformationSchema", "true");
            return DriverManager.getConnection(url,props);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static Connection getConnection(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            Properties properties = new Properties();
            properties.setProperty("user", username);
            properties.setProperty("password", password);
            properties.setProperty("remarks", "true");
            properties.setProperty("useInformationSchema", "true");
            return DriverManager.getConnection(url,properties);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

}



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static Connection conn = null;


    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/rewards";
            String user = "student";
            String password = "student";
            conn = DriverManager.getConnection(url + "?useSSL=false", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}



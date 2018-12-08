
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.lang.Integer.parseInt;

public class Login extends Connect {

    public User verify(String email) throws Exception {

        User user = new User();
        Connection conn = Connect.getConnection();
        PreparedStatement prepStmt;
        ResultSet rs;
        String query = "SELECT *  FROM Acct WHERE email=? ";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, email);
        rs = prepStmt.executeQuery();
        int rowCount = 0;
        while (rs.next()) {
            rowCount++;
            if (rowCount > 0) {
                return user;
            } else {
                return null;
            }
        }
        return null;
    }


    public User verify(String email, String pass) throws Exception {

        User user = new User();
        Connection conn = Connect.getConnection();
        PreparedStatement prepStmt;
        ResultSet rs;
        String query = "SELECT *  FROM Acct WHERE email=? and password=SHA1(?)";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, email);
        prepStmt.setString(2, pass);
        rs = prepStmt.executeQuery();
        int rowCount = 0;
        while (rs.next()) {
            user.userId = rs.getString("user_id");
            user.fName = rs.getString("first_name");
            user.lName = rs.getString("last_name");
            user.phone = rs.getString("phone");
            user.email = rs.getString("email");
            user.password = rs.getString("password");
            user.type = parseInt(rs.getString("type"));
            System.out.println(user.userId);
            rowCount++;
            if (rowCount > 0) {
                return user;
            }
        }

        return null;

    }
}

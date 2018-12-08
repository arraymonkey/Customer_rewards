


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.beans.property.SimpleStringProperty;

public class User extends Connect implements Account {
    String data1;
    String data2;
    String data3;


    String data4;
    String data5;

    String userId;
    String fName;
    String lName;

    String phone;
    String email;
    String password;
    PreparedStatement prepStmt;
    ResultSet rs;
    int type;

    public User(String data1, String data2, String data3, String data4, String data5) {
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
    }

    public User(String data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public String getData1() {
        return data1;
    }

    public void setData1(String data1) {
        this.data1 = data1;
    }

    public String getData2() {
        return data2;
    }

    public void setData2(String data2) {
        this.data2 = data2;
    }

    public User() throws Exception {

    }

    public String getData3() {
        return data3;
    }

    public void setData3(String data3) {
        this.data3 = data3;
    }

    public String getData4() {
        return data4;
    }

    public void setData4(String data4) {
        this.data4 = data4;
    }

    public String getData5() {
        return data5;
    }

    public void setData5(String data5) {
        this.data5 = data5;
    }


    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public ResultSet getAcct(String id) throws Exception {
        conn = Connect.getConnection();
        String query = "SELECT *  FROM Acct WHERE user_id=?";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, id);
        rs = prepStmt.executeQuery();
        return rs;

    }

    @Override
    public ResultSet getPHistory() throws Exception {
        conn = Connect.getConnection();
        String query = "select * from Purchase_H where user_id = ?;";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.userId);
        rs = prepStmt.executeQuery();
        return rs;

    }

    @Override
    public ResultSet getPoints() throws Exception {
        conn = Connect.getConnection();
        String query = "SELECT *  FROM Points WHERE user_id=?";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.userId);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public ResultSet getRewards() throws Exception {
        Connection conn = Connect.getConnection();
        String query = "SELECT *  FROM REWARDS.Rewards WHERE user_id=?";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.userId);
        rs = prepStmt.executeQuery();
        return rs;

    }

    @Override
    public ResultSet getRedeem() throws Exception {
        conn = Connect.getConnection();
        String query = "SELECT *  FROM Redeem WHERE user_id=?";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.userId);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public void updateAcct() throws Exception {
        conn = Connect.getConnection();
        String query = "UPDATE Acct SET phone=?, email=?, password=? WHERE user_id =?";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.phone);
        prepStmt.setString(2, this.email);
        prepStmt.setString(3, this.password);
        prepStmt.setString(4, this.userId);
        prepStmt.executeUpdate();
    }

    @Override
    public void redeemPoints(String points, String value) throws Exception {
        conn = Connect.getConnection();
        String query = "INSERT INTO Rewards(user_id, points, value, redeem_code) values (?,?,?, reward_code())";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, this.userId);
        prepStmt.setString(2, points);
        prepStmt.setString(3, value);
        prepStmt.execute();
    }


    @Override
    public void addAcct(String fname, String lname, String phone, String email, String pass) throws Exception {
        conn = Connect.getConnection();
        String query = "INSERT INTO Acct (first_name, last_name, phone, email, password)  values (?,?,?,?,SHA1(?))";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, fname);
        prepStmt.setString(2, lname);
        prepStmt.setString(3, phone);
        prepStmt.setString(4, email);
        prepStmt.setString(5, pass);
        prepStmt.execute();
    }

    @Override
    public ResultSet allPurchase() throws Exception {
        conn = Connect.getConnection();
        String query = "select a.user_id, a.first_name, a.last_name, p.amount, p.date_time from Acct a, Purchase_H p where a.user_id = p.user_id;";
        prepStmt = conn.prepareStatement(query);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public ResultSet searchPurchase(String fName, String lName) throws Exception {
        conn = Connect.getConnection();
        String query = "select * from (select a.user_id, a.first_name, a.last_name, p.amount, p.date_time from Acct a, Purchase_H p where a.user_id = p.user_id)as h where first_name =? or last_name=?;";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, fName);
        prepStmt.setString(2, lName);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public ResultSet allAcct() throws Exception {
        conn = Connect.getConnection();
        String query = "select user_id, first_name, last_name, phone ,email, password from Acct;";
        prepStmt = conn.prepareStatement(query);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public ResultSet searchAccount(String fName, String lName) throws Exception {
        conn = Connect.getConnection();
        String query = "select user_id, first_name,last_name,phone, email ,password from Acct where first_name = ? or last_name= ?;";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, fName);
        prepStmt.setString(2, lName);
        rs = prepStmt.executeQuery();
        return rs;
    }

    @Override
    public void addPurchase(String userid, String email, String amount) throws Exception {
        conn = Connect.getConnection();
        String query = "insert into Purchase_H(user_id, amount) select (select user_id from Acct where  email=? or user_id=?) ,?;";
        prepStmt = conn.prepareStatement(query);
        prepStmt.setString(1, email);
        prepStmt.setString(2, userid);
        prepStmt.setString(3, amount);

        prepStmt.execute();
    }
}
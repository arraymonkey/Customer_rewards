

import java.sql.ResultSet;

public interface Account {
    public ResultSet getAcct(String id) throws Exception;

    public ResultSet getPHistory() throws Exception;

    public ResultSet getPoints() throws Exception;

    public ResultSet getRewards() throws Exception;

    public ResultSet getRedeem() throws Exception;

    public void updateAcct() throws Exception;

    public void redeemPoints(String points, String value) throws Exception;


    public void addAcct(String fname, String lname, String phone, String email, String pass) throws Exception;

    public ResultSet allPurchase() throws Exception;

    public ResultSet searchPurchase(String lname, String email) throws Exception;

    ResultSet allAcct() throws Exception;

    ResultSet searchAccount(String fName, String lName) throws Exception;

    void addPurchase(String fname, String userid, String amount) throws Exception;
}

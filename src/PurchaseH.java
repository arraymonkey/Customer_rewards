import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.ResultSet;

public class PurchaseH extends AnchorPane {

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> userid;

    @FXML
    private TableColumn<User, String> fname;

    @FXML
    private TableColumn<User, String> lname;

    @FXML
    private TableColumn<User, String> amount;

    @FXML
    private TableColumn<User, String> date;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    ObservableList<User> data = FXCollections.observableArrayList();

    @FXML
    void onSearch(ActionEvent event) throws Exception {
        // if (firstname.getText().isEmpty() && lastname.getText().isEmpty()) {
        table.getItems().clear();
        searchPurchase();

    }

    public PurchaseH() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/history.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        allPurchase();
    }

    public void searchPurchase() throws Exception {
        User user = new User();
        ResultSet rs = user.searchPurchase(firstname.getText(), lastname.getText());
        while (rs.next()) {
            data.add(new User(rs.getString("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("amount"), rs.getString("date_time")));
        }

        userid.setCellValueFactory(new PropertyValueFactory<>("data1"));
        fname.setCellValueFactory(new PropertyValueFactory<>("data2"));
        lname.setCellValueFactory(new PropertyValueFactory<>("data3"));
        amount.setCellValueFactory(new PropertyValueFactory<>("data4"));
        date.setCellValueFactory(new PropertyValueFactory<>("data5"));
        table.setItems(data);
    }


    public void allPurchase() throws Exception {
        User user = new User();
        ResultSet rs = user.allPurchase();
        while (rs.next()) {
            data.add(new User(rs.getString("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("amount"), rs.getString("date_time")));
        }

        userid.setCellValueFactory(new PropertyValueFactory<>("data1"));
        fname.setCellValueFactory(new PropertyValueFactory<>("data2"));
        lname.setCellValueFactory(new PropertyValueFactory<>("data3"));
        amount.setCellValueFactory(new PropertyValueFactory<>("data4"));
        date.setCellValueFactory(new PropertyValueFactory<>("data5"));
        table.setItems(data);
    }

}

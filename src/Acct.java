import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;

public class Acct extends AnchorPane {

    @FXML
    private TableView<User> table;

    @FXML
    private TableColumn<User, String> userid;

    @FXML
    private TableColumn<User, String> fname;

    @FXML
    private TableColumn<User, String> lname;

    @FXML
    private TableColumn<User, String> phone;

    @FXML
    private TableColumn<User, String> email;

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    ObservableList<User> data = FXCollections.observableArrayList();

    @FXML
    void onSearch(ActionEvent event)throws Exception {
        table.getItems().clear();
        searchAcct();

    }

    public Acct() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/acct.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        allAcct();

    }

    public void searchAcct() throws Exception {
        User user = new User();
        ResultSet rs = user.searchAccount(firstname.getText(), lastname.getText());
        while (rs.next()) {
            data.add(new User(rs.getString("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"), rs.getString("email")));
        }

        userid.setCellValueFactory(new PropertyValueFactory<>("data1"));
        fname.setCellValueFactory(new PropertyValueFactory<>("data2"));
        lname.setCellValueFactory(new PropertyValueFactory<>("data3"));
        phone.setCellValueFactory(new PropertyValueFactory<>("data4"));
        email.setCellValueFactory(new PropertyValueFactory<>("data5"));
        table.setItems(data);
    }

    public void allAcct() throws Exception {
        User user = new User();
        ResultSet rs = user.allAcct();
        while (rs.next()) {
            data.add(new User(rs.getString("user_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone"), rs.getString("email")));
        }

        userid.setCellValueFactory(new PropertyValueFactory<>("data1"));
        fname.setCellValueFactory(new PropertyValueFactory<>("data2"));
        lname.setCellValueFactory(new PropertyValueFactory<>("data3"));
        phone.setCellValueFactory(new PropertyValueFactory<>("data4"));
        email.setCellValueFactory(new PropertyValueFactory<>("data5"));
        table.setItems(data);
    }


}

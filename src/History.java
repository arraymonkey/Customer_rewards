import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class History extends AnchorPane {

    ObservableList<User> data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane pane;
    @FXML
    public TableView<User> table;
    @FXML
    public TableColumn<User, String> amount;

    @FXML
    public TableColumn<User, String> date;

    public History(User user) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/PurchaseHistory.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
        try {
            ResultSet rs = user.getPHistory();
            while (rs.next()) {
                data.add(new User(rs.getString("amount"), rs.getString("date_time")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        amount.setCellValueFactory(new PropertyValueFactory<>("data1"));
        date.setCellValueFactory(new PropertyValueFactory<>("data2"));
        table.setItems(data);
    }

}






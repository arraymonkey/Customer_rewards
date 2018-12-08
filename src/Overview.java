import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.text.Text;

import javafx.event.ActionEvent;

import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

public class Overview extends AnchorPane {
    User user;
    @FXML
    private AnchorPane overview;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private Text txt_points;

    @FXML
    private TableView<User> table_rewards;

    @FXML
    private TableColumn<User, String> code;

    @FXML
    private TableColumn<User, String> value;

    @FXML
    private Button bntRedeem;

    @FXML
    ObservableList<User> data = FXCollections.observableArrayList();

    String points = "";
    String values="";




    @FXML
    void onRedeem(ActionEvent event) throws Exception {
        user.redeemPoints(points,values);
        bntRedeem.setVisible(false);
        getTotal(user);
        getReward();



    }

    public void getTotal(User user) throws Exception{
        double progressIndicator;
            ResultSet rs = user.getPoints();
            while (rs.next()) {
                points = rs.getString("points");
                values = rs.getString("value");


                if (Double.parseDouble(points) > 400) {
                    progressIndicator = 1.00;
                    bntRedeem.setVisible(true);

                } else {
                    progressIndicator = Double.parseDouble(points) / 400;

                }
                progress.setProgress(progressIndicator);
                txt_points.setText(points);
            }



    }

    public void getReward() throws Exception {

            ResultSet rs = user.getRewards();
            while (rs.next()) {
                data.add(new User(rs.getString("redeem_code"), rs.getString("value")));
            }

        code.setCellValueFactory(new PropertyValueFactory<>("data1"));
        value.setCellValueFactory(new PropertyValueFactory<>("data2"));
        table_rewards.setItems(data);
    }


    public Overview(User user) throws Exception {
        this.user = user;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/Overview.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
        bntRedeem.setVisible(false);
        getTotal(user);
        getReward();


    }
}

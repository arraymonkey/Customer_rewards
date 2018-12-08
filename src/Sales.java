

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

public class Sales extends AnchorPane {

    @FXML
    private TextField email;

    @FXML
    private TextField amount;

    @FXML
    private TextField userid;

    @FXML
    void onSale(ActionEvent event) throws Exception {
        User user = new User();
        user.addPurchase(userid.getText(), email.getText(), amount.getText());

    }


    public Sales() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/sale.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

    }

}

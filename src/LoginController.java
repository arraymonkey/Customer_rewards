
import java.net.URL;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController extends AnchorPane {

    public LoginController() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/Login.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

    }

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField txt_email_login;
    @FXML
    private PasswordField txt_pass_login;

    @FXML
    private PasswordField txt_pass;

    @FXML
    private Button bnt_login;

    @FXML
    private TextField txt_fname;

    @FXML
    private TextField txt_lname;

    @FXML
    private TextField txt_phone;

    @FXML
    private PasswordField txt_pass_confirm;

    @FXML
    private TextField txt_email;

    @FXML
    void onLogin(ActionEvent e) throws Exception {
        String email = txt_email_login.getText().trim();
        String pass = txt_pass_login.getText().trim();

        if (email.isEmpty() || pass.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Please Enter Your Email & Password!");
            alert.showAndWait();
        } else {
            Login login = new Login();
            User user = login.verify(email, pass);
            if (user == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Password or Email is incorrect!!");
                alert.showAndWait();
            } else {
                if (user.type == 1) {
                    rootPane.getChildren().setAll(new Admin());
                } else {
                    rootPane.getChildren().setAll(new UserPanelController(user));
                }


            }

        }

    }

    @FXML
    void onRegister() throws Exception {

        if (!(txt_pass.getText().isEmpty()) || (!(txt_pass_confirm.getText().isEmpty()))) {
            if (txt_pass.getText().contentEquals(txt_pass_confirm.getText())) {
                Login login = new Login();
                User user = login.verify(txt_email.getText());
                if (user == null) {
                    User newUser = new User();
                    newUser.addAcct(txt_fname.getText(), txt_lname.getText(), txt_phone.getText(), txt_email.getText(), txt_pass.getText());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("YOU'RE IN");
                    alert.setHeaderText("GOT BACT TO THE LOGIN SCREEN");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Someone is registered under that email already");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Your password does not match!");
                alert.showAndWait();
            }
        }

    }

}

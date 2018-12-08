import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class Info extends AnchorPane {

    @FXML
    public TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField comfirmpass;

    @FXML
    private AnchorPane bntupdate;

    @FXML
    void onUpdate(ActionEvent event) throws Exception {
        if (!(phone.getText().isEmpty())) {
            user.setPhone(phone.getText());
        }
        if (!(email.getText().isEmpty())) {
            user.setEmail(email.getText());
        }
        if(!(pass.getText().isEmpty()) || (!(comfirmpass.getText().isEmpty()))){
            if(pass.getText().contentEquals(comfirmpass.getText())){
                user.setPassword(pass.getText());
                user.updateAcct();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Your password does not match!");
                alert.showAndWait();
            }
        }
        setPromt();
    }

    User user;

    public Info(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/info.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        this.user = user;
        fname.setEditable(false);
        lname.setEditable(false);
        setPromt();
    }

    public void setPromt() {
        fname.setText(user.getfName());
        lname.setText(user.getlName());
        phone.setPromptText(user.getPhone());
        email.setPromptText(user.getEmail());
    }
}

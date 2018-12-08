
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.sql.ResultSet;


public class UserPanelController extends AnchorPane {
    @FXML
    private Pane mainpane;

    @FXML
    private VBox sideNav;

    @FXML
    private AnchorPane panel, apane;
    User user;
    @FXML
    private Label label_fname;


    public UserPanelController(User user) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gui/UserPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        this.user = user;
        label_fname.setText(user.getfName());
        panel.getChildren().add(new Overview(user));

        for (Node node : sideNav.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    switch (node.getAccessibleText()) {
                        case "bnt_one":
                            panel.getChildren().clear();
                            try {
                                panel.getChildren().add(new Overview(user));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case "bnt_two":
                            panel.getChildren().clear();

                            try {
                                panel.getChildren().add(new Info(user));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "bnt_three":
                            panel.getChildren().clear();

                            try {
                                panel.getChildren().add(new History(user));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "bnt_four":
                            this.user= null;
                            mainpane.getChildren().clear();
                            try {
                                mainpane.getChildren().setAll(new LoginController());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                });
            }

        }

    }

}

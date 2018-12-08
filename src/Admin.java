
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Admin extends AnchorPane {

    @FXML
    private VBox sideNav;

    @FXML
    private AnchorPane viewpane, mainpane;

    public Admin() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/admin.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();


        for (Node node : sideNav.getChildren()) {
            if (node.getAccessibleText() != null) {
                node.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    switch (node.getAccessibleText()) {
                        case "bnt_one":
                            viewpane.getChildren().clear();
                            try {
                                viewpane.getChildren().add(new Sales());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            break;
                        case "bnt_two":
                            viewpane.getChildren().clear();

                            try {
                                viewpane.getChildren().add(new Acct());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "bnt_three":
                            viewpane.getChildren().clear();

                            try {
                                viewpane.getChildren().add(new PurchaseH());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;
                        case "bnt_four":
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

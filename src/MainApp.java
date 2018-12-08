
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class MainApp extends Application {

	static Stage stage;

	@Override
	public void start(Stage primaryStage) {
		try {

			Scene scene = new Scene(new LoginController());
			scene.getStylesheets().add(getClass().getResource("gui/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		launch(args);
	}
}

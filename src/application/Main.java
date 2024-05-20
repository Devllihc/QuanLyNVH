package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Image icon = new Image("/image/ionia.png");
		Parent root = FXMLLoader.load(getClass().getResource("/view/Loggin.fxml"));
		Stage stage = new Stage();
		Scene scene = new Scene(root);
		String css = this.getClass().getResource("/Style/style1.css").toExternalForm();
		scene.getStylesheets().add(css);
		stage.setScene(scene);
		stage.getIcons().add(icon);
		stage.setTitle("Quản lý nhà văn hóa");
		stage.show();
	}
}

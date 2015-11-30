import controller.TourController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		String viewerFxml = "view/room_tour.fxml";
		AnchorPane page = (AnchorPane) fxmlLoader.load(this.getClass().getResource(viewerFxml).openStream());
		Scene scene = new Scene(page);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Room Tour");
		
		//≥ı ºªØ
		TourController controller = fxmlLoader.getController();
		controller.Initialise();
		
		primaryStage.show();
		
	}
	
	public static void main(String args[]) {
	     launch(args);
	     System.exit(0);
	}

}

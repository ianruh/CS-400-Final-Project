package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
  
	@Override
	public void start(Stage primaryStage) {
		try {
			// Set main page title
			primaryStage.setTitle("Quiz Generator");
			
			// Set the layout as an instance of MainMenu
			MainMenu mainMenu = new MainMenu(primaryStage);

			// Open the scene
			Scene scene = new Scene(mainMenu ,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

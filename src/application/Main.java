package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
  
	@Override
	public void start(Stage primaryStage) {
		try {
			// These are singletons, so ignore the compiler's warning about not being used.
			@SuppressWarnings("unused")
			ImportExportUtility importExport = new ImportExportUtility(primaryStage);
			@SuppressWarnings("unused")
			QuestionBank questionBank = new QuestionBank();
			
			// Set main page title
			primaryStage.setTitle("Quiz Generator");
			
			// Set the layout as an instance of MainMenu
			MainMenu mainMenu = new MainMenu();

			// Open the scene
			Scene scene = new Scene(mainMenu ,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			
			// Print any exceptions 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Launch the application
		launch(args);
	}
}

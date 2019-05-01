/**
 * Final Project. Quiz Generator.
 *
 * Filename:   Main
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * @author Preston Lewis, ID 9074531329, prlewis@wisc.edu, lecture 004      
 * @author Jared Krahn, ID 9076949693, jkrahn2@wisc.edu, lecture 004
 * @author Ian Ruh, ID 9080231591, iruh@wisc.edu, lecture 004
 * @author Emily Binversie ID 9063469945, eebinversie@wisc.edu, lecture 004
 *
 * Due Date:   05/02/2019 at 10pm
 * 
 */
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

package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
  
  //MainMenu mainMenu = new MainMenu();
  ObservableList<String> weekDays =
      FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
  
  PickTopicAndQuestion pickTopicsPage = new PickTopicAndQuestion(weekDays);
  
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Quiz Generator");
//			BorderPane root = new BorderPane();
//			
//			pickTopicsPage.setAlignment(Pos.CENTER);
//			
//			root.setCenter(pickTopicsPage);
			MainMenu mainMenu = new MainMenu(10);

			
			Scene scene = new Scene(mainMenu ,600,900);
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

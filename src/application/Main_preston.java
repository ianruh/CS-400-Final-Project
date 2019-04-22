package application;
	
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
  
  ObservableList<String> trueOrFalse =
      FXCollections.observableArrayList("True", "False");
  
  AddQuestion addQ = new AddQuestion();
  PickTopicAndQuestion pQ = new PickTopicAndQuestion(trueOrFalse);
  
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			pQ.setAlignment(Pos.CENTER);
			
			root.setCenter(pQ);
	

			
			Scene scene = new Scene(root,400,400);
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

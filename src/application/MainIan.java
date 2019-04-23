package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;


public class MainIan extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,600,900);
			
			
			
			
			
			BasicQuiz quiz = new BasicQuiz(QuestionBank.getQuestions(), new FinishQuizHandler() {

				@Override
				public void handleFinish() {
					root.setCenter(new Label("You finished."));
				}
				
			});
			
			root.setCenter(quiz);
			
			
			
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

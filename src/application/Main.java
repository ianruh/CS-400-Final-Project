package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			
		    primaryStage.setTitle("Quiz Generator: Main Menu");
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
            Button button1 = new Button("Load/Import Questions");
            Button button2 = new Button("Add Questions");
            Button button3 = new Button("Start Quiz");
            Button button4 = new Button("Exit");
            VBox vbox = new VBox(button1, button2, button3, button4);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(40);
            
            int totalNumQuestions = 0;
            Label reportTotalQuestions = new Label("There are " + totalNumQuestions + " questions in the database");
           
            //reportTotalQuestions.setAlignment(Pos.CENTER);
            root.setBottom(reportTotalQuestions);
            root.setAlignment(reportTotalQuestions, Pos.CENTER);
     
            
            
            root.setCenter(vbox);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}



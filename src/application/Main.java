package application;
	
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
			Image quizBackground = new Image("background3.png", 400, 400, false, true);
			
			
			
			   BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

			   Background background = new Background(new BackgroundImage(quizBackground,
		            BackgroundRepeat.NO_REPEAT,
		            BackgroundRepeat.NO_REPEAT,
		            BackgroundPosition.CENTER,
		            bSize));
			   
			   
			   root.setBackground(background);


			
            Button buttonLoadImport = new Button("Load/Import Questions");
            Button buttonAddQuestion = new Button("Add Questions");
            Button buttonStartQuiz = new Button("Start Quiz");
            Button buttonExit = new Button("Exit");
            VBox vbox = new VBox(buttonLoadImport, buttonAddQuestion, buttonStartQuiz, buttonExit);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(40);
            
            int totalNumQuestions = 0;
            Label reportTotalQuestions = new Label("There are " + totalNumQuestions + " questions in the database");
  
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

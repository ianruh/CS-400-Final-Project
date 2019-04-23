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
import javafx.scene.paint.Color;


public class MainMenu extends BorderPane {

  // class field int that stores the number of questions in the questionBank
  int totalNumQuestions;

  public MainMenu(int totalNumQuestions) {

    addComponents();

  }
  
  private void addComponents() {
		this.totalNumQuestions = totalNumQuestions;
		
		// TODO: The image doesn't work for everybody for some reason.
		
		//create and set the main menu background
		//	    Image quizBackground = new Image("background3.png", 400, 400, false, true);
		//	    BackgroundSize bSize =
		//	        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		//	    Background background = new Background(new BackgroundImage(quizBackground,
		//	        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize));
		//	    this.setBackground(background);
		
		// create the main menu buttons and add them to a Vbox in the center of the borderpane
		Button buttonLoadImport = new Button("Load/Import Questions");
		Button buttonAddQuestion = new Button("Add Questions");
		Button buttonStartQuiz = new Button("Start Quiz");
		
		buttonStartQuiz.setOnMouseClicked(e -> this.startQuizPressed());
		
		Button buttonExit = new Button("Exit");
		VBox vbox = new VBox(buttonLoadImport, buttonAddQuestion, buttonStartQuiz, buttonExit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(40);
		this.setCenter(vbox);
		
		// add a label to the bottom that reports the number of questions in the questionBank
		Label reportTotalQuestions =
		    new Label("There are " + totalNumQuestions + " questions in the database");
		reportTotalQuestions.setTextFill(Color.web("#008000"));
		this.setBottom(reportTotalQuestions);
		this.setAlignment(reportTotalQuestions, Pos.CENTER);
  }
  
  private void startQuizPressed() {
	  FinishQuizHandler finishHandler = () -> addComponents();
	  
	  BeginQuizHandler beginHandler = new BeginQuizHandler() {
		@Override
		public void handleBegin() {
			BasicQuiz quiz = new BasicQuiz(QuestionBank.getQuestions(), finishHandler);
			setCenter(quiz);
		}  
	  };
		  
	  
	  StartQuiz startQuiz = new StartQuiz(beginHandler, finishHandler);
	  this.setCenter(startQuiz.returnScene());
  }
}
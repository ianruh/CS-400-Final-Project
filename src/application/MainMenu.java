package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    this.totalNumQuestions = totalNumQuestions;
    addComponents();

  }
  
  private void addComponents() {
		
		
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
		
		// how to handle the buttons being clicked on, link them to the next screen
		buttonLoadImport.setOnMouseClicked(e -> this.importPressed());
	    buttonStartQuiz.setOnMouseClicked(e -> this.startQuizPressed());
	    buttonAddQuestion.setOnMouseClicked(e -> this.newQuestionPressed());
	    buttonExit.setOnMouseClicked(e -> this.exitPressed());
	    //need to load/import questions

	    
		
  }
  
  private void startQuizPressed() {
	  EventHandler finishHandler = () -> addComponents();
	  
	  EventHandler beginHandler = new EventHandler() {
		@Override
		public void handleEvent() {
			BasicQuiz quiz = new BasicQuiz(QuestionBank.getQuestions(), finishHandler, () -> {
				StartQuiz startQuiz = new StartQuiz(this, finishHandler);
				setCenter(startQuiz.returnScene());
			});
			setCenter(quiz);
		}  
	  };
	  
	  StartQuiz startQuiz = new StartQuiz(beginHandler, finishHandler);
	  this.setCenter(startQuiz.returnScene());
  }
  
  private void newQuestionPressed() {
	  EventHandler finishHandler = () -> addComponents();
	  
	  ObservableList<String> weekDays =
		      FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
	  this.setCenter(new PickTopicAndQuestion(weekDays, finishHandler));
  }
  
  private void exitPressed() {
    EventHandler finishHandler = () -> addComponents();    
    this.setCenter(new ExitAndSaveMenu(finishHandler));
    
  }
  
  private void importPressed() {
    EventHandler finishHandler = () -> addComponents();
    this.setCenter(new ImportQuestions(finishHandler));
    
  }
  
  private void exportPressed() {
    EventHandler finishHandler = () -> addComponents();
    this.setCenter(new ExportQuestions(finishHandler));
    
  }
}
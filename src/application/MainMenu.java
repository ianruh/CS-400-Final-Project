package application;

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

  public MainMenu() {
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
		
		// Title
		Label applicationTitle = new Label("Welcome to Quiz Generator!\n ");
		applicationTitle.setScaleX(4.0);
		applicationTitle.setScaleY(4.0);

		// Load/Import Button
		Button buttonLoadImport = new Button("Load/Import Questions from a File");
		buttonLoadImport.setPrefSize(256, 56);

		// Add Questions Button
		Button buttonAddQuestion = new Button("Create a New Question");
		buttonAddQuestion.setPrefSize(256, 56);

		// Start Quiz Button
		Button buttonStartQuiz = new Button("Start Quiz");
		buttonStartQuiz.setPrefSize(256, 56);

		// Exit Button
		Button buttonExit = new Button("Exit");
		buttonExit.setPrefSize(256, 56);

		VBox vbox = new VBox(applicationTitle, buttonLoadImport, buttonAddQuestion, buttonStartQuiz, buttonExit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(40);
		this.setCenter(vbox);
		
		// add a label to the bottom that reports the number of questions in the questionBank
		Label reportTotalQuestions =
		    new Label("There are " + QuestionBank.master.getNumQuestions() + " questions in the database");
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
	  
	  StartQuiz startQuiz = new StartQuiz(finishHandler);
	  this.setCenter(startQuiz);
  }
  
  private void newQuestionPressed() {
	  EventHandler finishHandler = () -> addComponents();
	  
	  ObservableList<String> weekDays =
		      FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
	  this.setCenter(new PickTopicAndQuestion(weekDays, finishHandler));
  }
  
  private void exitPressed() {
    EventHandler cancelHandler = () -> addComponents();    
    this.setCenter(new ExitAndSaveMenu(cancelHandler));
    
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
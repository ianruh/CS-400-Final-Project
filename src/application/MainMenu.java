/**
 * Final Project. Quiz Generator.
 *
 * Filename:   MainMenu
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

import java.io.File;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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

	// Default constructor
	public MainMenu() {
		addComponents();
	}

	// Create main page
	private void addComponents() {

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
		
		// Save Question Bank
		Button buttonSave = new Button("Save Question Bank");
		buttonSave.setPrefSize(256, 56);
		
		// Exit Button
		Button buttonExit = new Button("Exit");
		buttonExit.setPrefSize(256, 56);

		VBox vbox = new VBox(applicationTitle, buttonLoadImport, buttonAddQuestion,
							 buttonStartQuiz, buttonSave, buttonExit);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(40);
		this.setCenter(vbox);

		// Label presenting total # of questions in bank
		Label reportTotalQuestions = new Label(
				"There are " + QuestionBank.master.getNumQuestions() + " questions in the database");
		reportTotalQuestions.setTextFill(Color.web("#008000"));
		this.setBottom(reportTotalQuestions);
		this.setAlignment(reportTotalQuestions, Pos.CENTER);

		// Handle mouse interactions
		buttonLoadImport.setOnMouseClicked(e -> this.importPressed());
		buttonStartQuiz.setOnMouseClicked(e -> this.startQuizPressed());
		buttonAddQuestion.setOnMouseClicked(e -> this.newQuestionPressed());
		buttonExit.setOnMouseClicked(e -> this.exitPressed());
		buttonSave.setOnMouseClicked(e -> this.exportQuestionBank());
	}

	// Import interaction event
	private void importPressed() {
		File file = ImportExportUtility.master.selectJSONFile();
		if (file != null) {
			List<BasicQuestion> list = ImportExportUtility.master.importQuestions(file);
			QuestionBank.master.addQuestions(list);
		}
		this.addComponents();
	}

	// New question interaction event
	private void newQuestionPressed() {
		EventHandler finishHandler = () -> addComponents();

		ObservableList<String> topics = FXCollections.observableArrayList(QuestionBank.master.getTopics());

		this.setCenter(new PickTopicAndQuestion(topics, finishHandler));
	}

	// Start quiz interaction event
	private void startQuizPressed() {
		EventHandler finishHandler = () -> addComponents();
		StartQuiz startQuiz = new StartQuiz(finishHandler);
		this.setCenter(startQuiz);
	}
	
	// Export Question Bank
	private void exportQuestionBank() {
		
		// Success
    	EventHandler exportHandler = () -> {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Success!");
	    	alert.setHeaderText("Quiz bank successfully exported!");
	    	Optional<ButtonType> result = alert.showAndWait(); 
    	};
		ImportExportUtility.master.exportQuestionBank(exportHandler);
	}

	// Exit interaction event
	private void exitPressed() {
		EventHandler cancelHandler = () -> addComponents();
		this.setCenter(new ExitAndSaveMenu(cancelHandler));
	}

}
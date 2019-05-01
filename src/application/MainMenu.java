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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenu extends BorderPane {

  /**
   * Default constructor for the main menu
   */
    public MainMenu() {
        addComponents();
    }

    /**
     * Create main page for the main menu
     */
    private void addComponents() {

        // Create and add a welcome title
        Label applicationTitle = new Label("Welcome to Quiz Generator!\n ");
        applicationTitle.setScaleX(4.0);
        applicationTitle.setScaleY(4.0);

        // Create a Load/Import Button
        Button buttonLoadImport = new Button("Load/Import Questions from a File");
        buttonLoadImport.setPrefSize(256, 56);

        // Create a Questions Button
        Button buttonAddQuestion = new Button("Create a New Question");
        buttonAddQuestion.setPrefSize(256, 56);

        // Create a start Quiz Button
        Button buttonStartQuiz = new Button("Start Quiz");
        buttonStartQuiz.setPrefSize(256, 56);
        
        // Create a save Question Bank Button
        Button buttonSave = new Button("Save Question Bank");
        buttonSave.setPrefSize(256, 56);
        
        // Create a Exit Button
        Button buttonExit = new Button("Exit");
        buttonExit.setPrefSize(256, 56);

        // Add all the created buttons to a vertical box and center them on the screen
        VBox vbox = new VBox(applicationTitle, buttonLoadImport, buttonAddQuestion,
                             buttonStartQuiz, buttonSave, buttonExit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(40);
        this.setCenter(vbox);

        // Label that reports the total # of questions in question bank aka database
        Label reportTotalQuestions = new Label(
                "There are " + QuestionBank.master.getNumQuestions() 
                + " questions in the database");
        reportTotalQuestions.setTextFill(Color.web("#008000"));
        this.setBottom(reportTotalQuestions);
        BorderPane.setAlignment(reportTotalQuestions, Pos.CENTER);

        // Call the appropriate method to handle mouse interaction so a user can interact
        // with the interface
        buttonLoadImport.setOnMouseClicked(e -> this.importPressed());
        buttonStartQuiz.setOnMouseClicked(e -> this.startQuizPressed());
        buttonAddQuestion.setOnMouseClicked(e -> this.newQuestionPressed());
        buttonExit.setOnMouseClicked(e -> this.exitPressed());
        buttonSave.setOnMouseClicked(e -> this.exportQuestionBank());
    }

    /**
     * Import interaction event - import a JSON file of questions
     */
    private void importPressed() {
        // read in the json file the user selects
        File file = ImportExportUtility.master.selectJSONFile();
        
        // if the file to read in is faulty, handle that
        if (file != null) {
            List<BasicQuestion> list = ImportExportUtility.master.importQuestions(file);
            QuestionBank.master.addQuestions(list);
        }
        this.addComponents();
    }

    /**
     * New question interaction event - take the user to the page to let them create an 
     * individual question to add to the Question Bank 
     */
    private void newQuestionPressed() {
        EventHandler finishHandler = () -> addComponents();

        // feed in the already available list of topis in the Question Bank 
        ObservableList<String> topics = FXCollections.observableArrayList(QuestionBank.master.getTopics());

        this.setCenter(new PickTopicAndQuestion(topics, finishHandler));
    }

    /**
     * Start quiz interaction event - take the user to the start quiz page
     */
    private void startQuizPressed() {
        EventHandler finishHandler = () -> addComponents();
        StartQuiz startQuiz = new StartQuiz(finishHandler);
        this.setCenter(startQuiz);
    }
    
    /**
     * Export Question Bank - allow the user to save the questions to a JSON
     */
    private void exportQuestionBank() {
        
        // report to the user the questions are successfully exported to a JSON
        EventHandler exportHandler = () -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Quiz bank successfully exported!");
            alert.showAndWait(); 
        };
        ImportExportUtility.master.exportQuestionBank(exportHandler);
    }

    /**
     * Exit interaction event - allow the user to exit the program and ask them if they would
     * like to save the questions to a JSON
     */
    private void exitPressed() {
        EventHandler cancelHandler = () -> addComponents();
        this.setCenter(new ExitAndSaveMenu(cancelHandler));
    }
}
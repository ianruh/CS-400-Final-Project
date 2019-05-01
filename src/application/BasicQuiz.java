/**
 * Final Project. Quiz Generator.
 *
 * Filename:   BasicQuiz
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

import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The BasicQuiz class implements the Quiz interface and extends a ScrollPane.
 *
 */
public class BasicQuiz extends VBox implements Quiz {
	
	/**
	 * The ResultsBox class is convenience class to contain the results box.
	 *
	 */
	private class ResultsBox extends VBox {
		
		/**
		 * Default constructor
		 * @param numCorrect Number of question answered correctly.
		 * @param numWrong Number of questions answered wrong.
		 * @param handler Handler for finishing the quiz.
		 */
		private ResultsBox(int numCorrect, int total,
				EventHandler finishHandler,
				EventHandler newQuizHandler,
				EventHandler saveQuizHandler) {
			super(10);
			HBox labels = new HBox(10);
			HBox buttons = new HBox(10);
			
			// Create the elements
			Label percentageLabel = new Label("Percentage: " + (((double)numCorrect / (double)total)*100) + "%");
			Label numRightWrongLabel = new Label(numCorrect + "/" + total);
			Button finishButton = new Button("Finish");
			Button newQuizButton = new Button("Make New Quiz");
			Button saveQuizButton = new Button("Save Quiz");
			
			// Add styling
			percentageLabel.getStyleClass().add("finish-quiz-items");
			numRightWrongLabel.getStyleClass().add("finish-quiz-items");
			finishButton.getStyleClass().add("finish-quiz-items");
			newQuizButton.getStyleClass().add("finish-quiz-items");
			saveQuizButton.getStyleClass().add("finish-quiz-items");
			buttons.getStyleClass().add("quiz-finish-buttons");
			this.getStyleClass().add("question-card");
			this.setMaxWidth(500);
			
			// Attach handlers
			finishButton.setOnMouseClicked(e -> finishHandler.handleEvent());
			newQuizButton.setOnMouseClicked(e -> newQuizHandler.handleEvent());
			saveQuizButton.setOnMouseClicked(e -> saveQuizHandler.handleEvent());
			
			// Add components to the main component
			labels.getChildren().add(percentageLabel);
			labels.getChildren().add(numRightWrongLabel);
			buttons.getChildren().add(finishButton);
			buttons.getChildren().add(newQuizButton);
			buttons.getChildren().add(saveQuizButton);
			
			// Add main containers to this
			this.getChildren().add(labels);
			this.getChildren().add(buttons);
		}
	}
	
	/**
	 * Class that encapsulates a bar under each question.
	 *
	 */
	private class NextBar extends HBox {
		/**
		 * Basic constructor that takes a handler for the next button.
		 * @param nextHandler
		 */
		private NextBar(EventHandler nextHandler) {
			super(15);
			Button nextButton = new Button("Next");
			
			// Handler
			nextButton.setOnMouseClicked((e) -> nextHandler.handleEvent());
			
			// Styling
			nextButton.getStyleClass().add("finish-quiz-items");
			this.getStyleClass().add("question-card");
			this.setMaxWidth(500);
			
			// Add component
			this.getChildren().add(nextButton);
		}
	}
	
	// Store the state of the quiz
	private int numCorrect = 0;
	private int numWrong = 0;
	private int numQuestionsShowing = 0;
	
	// Store a list of the questions.
	protected List<BasicQuestion> questions;
	
	// A storage reference for the event handlers
	private EventHandler finishHandler;
	private EventHandler newQuizHandler;
	
	// The single next question bar
	NextBar nextBar = new NextBar(() -> this.nextQuestion());
	
	// The single results bar
	ResultsBox resultsBox = null;
	
	
	/**
	 * Constructor for a quiz.
	 * @param questions ArrayList of questions to put in the quiz.
	 * @param finishHandler Handler for what to do when the quiz is completed.
	 */
	public BasicQuiz(List<BasicQuestion> questions, EventHandler finishHandler, EventHandler newQuizHandler) {
		super(15);
		// Store the questions.
		this.questions = questions;
		
		// Store the handler
		this.finishHandler = finishHandler;
		this.newQuizHandler = newQuizHandler;
		
		// Set the properties
		this.getStyleClass().add("quiz-container");
		
		// Add the questions to the vertical box.
		nextQuestion();
		
	}
	
	/**
	 * Adds all of the questions to the vertical box.
	 */
	private void nextQuestion() {
		this.getChildren().clear();
		final int INDEX = this.numQuestionsShowing;
		this.questions.get(this.numQuestionsShowing).addAnsweredHandler(() -> checkAllAnswered(INDEX));
		this.questions.get(this.numQuestionsShowing).setIndex(INDEX);
		this.getChildren().add(this.questions.get(this.numQuestionsShowing));
		this.numQuestionsShowing++;
		this.getChildren().add(nextBar);
		nextBar.setVisible(false);
	}

	/**
	 * Gets the percentage
	 */
	@Override
	public double getPercentage() {
		return ((double)(this.numCorrect) / (double)(this.numWrong + this.numCorrect))*100;
	}
	
	/**
	 * Check if all of the questions have been answered, and if so, show the results.
	 */
	private void checkAllAnswered(int index) {
		// Check if the next button should be shown.
		if(index != this.questions.size()-1)
			nextBar.setVisible(true);
		
		// Check that all the questions have been answered.
		boolean allAnswered = this.questions.size()-1 == index;
		
		// If all have been answered, display the results box
		if(allAnswered && this.resultsBox == null) {
			for(int i = 0; i < this.questions.size(); i++) {
				if(this.questions.get(i).answeredCorrectly) {
					this.numCorrect++;
				} else {
					this.numWrong++;
				}
			}
			
			EventHandler saveQuizHandler = () -> {
				resetQuestions();
	            ImportExportUtility.master.exportQuiz(this, () -> this.finishHandler.handleEvent());
			};
			
			EventHandler resetAndFinishHandler = () -> {
				resetQuestions();
				this.finishHandler.handleEvent();
			};
			
			EventHandler resetNewQuizHandler = () -> {
				resetQuestions();
				this.newQuizHandler.handleEvent();
			};
			
			this.resultsBox = new ResultsBox(this.numCorrect, 
					(this.numWrong + this.numCorrect), 
					resetAndFinishHandler, 
					resetNewQuizHandler, 
					saveQuizHandler);
			
			this.getChildren().remove(this.nextBar);
			this.getChildren().add(resultsBox);
		}
	}
	
	/**
	 * Utility method to reset the state of the question objects.
	 */
	private void resetQuestions() {
		for(BasicQuestion question: this.questions) {
			question.answered = false;
			question.answeredCorrectly = false;
			question.getChildren().remove(question.answerCheckLabel);
		}
	}
}

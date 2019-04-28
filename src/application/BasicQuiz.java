package application;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The BasicQuiz class implements the Quiz interface and extends a ScrollPane.
 * @author ianruh
 *
 */
public class BasicQuiz extends ScrollPane implements Quiz {
	
	/**
	 * The ResultsBox class is convenience class to contain the results box.
	 * @author ianruh
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
			
			Label percentageLabel = new Label("Percentage: " + (((double)numCorrect / (double)total)*100) + "%");
			Label numRightWrongLabel = new Label(numCorrect + "/" + total);
			Button finishButton = new Button("Finish");
			Button newQuizButton = new Button("Make New Quiz");
			Button saveQuizButton = new Button("Save Quiz");
			
			percentageLabel.getStyleClass().add("finish-quiz-items");
			numRightWrongLabel.getStyleClass().add("finish-quiz-items");
			finishButton.getStyleClass().add("finish-quiz-items");
			newQuizButton.getStyleClass().add("finish-quiz-items");
			saveQuizButton.getStyleClass().add("finish-quiz-items");
			
			buttons.getStyleClass().add("quiz-finish-buttons");
			
			finishButton.setOnMouseClicked(e -> finishHandler.handleEvent());
			newQuizButton.setOnMouseClicked(e -> newQuizHandler.handleEvent());
			saveQuizButton.setOnMouseClicked(e -> saveQuizHandler.handleEvent());
			
			labels.getChildren().add(percentageLabel);
			labels.getChildren().add(numRightWrongLabel);
			
			buttons.getChildren().add(finishButton);
			buttons.getChildren().add(newQuizButton);
			buttons.getChildren().add(saveQuizButton);
			
			this.getStyleClass().add("question-card");
			
			this.getChildren().add(labels);
			this.getChildren().add(buttons);
		}
	}
	
	// Store the state of the quiz
	private int numCorrect = 0;
	private int numWrong = 0;
	private int numQuestionsShowing = 0;
	
	// Store a list of the questions.
	protected List<BasicQuestion> questions;
	
	// The vertical box used for the layout
	private VBox verticalBox;
	
	// A storage reference for the event handlers
	private EventHandler finishHandler;
	private EventHandler newQuizHandler;
	
	
	/**
	 * Constructor for a quiz.
	 * @param questions ArrayList of questions to put in the quiz.
	 * @param finishHandler Handler for what to do when the quiz is completed.
	 */
	public BasicQuiz(List<BasicQuestion> questions, EventHandler finishHandler, EventHandler newQuizHandler) {
		super();
		
		//Initialize the vertical box
		this.verticalBox = new VBox(20);
		
		// Store the questions.
		this.questions = questions;
		
		// Store the handler
		this.finishHandler = finishHandler;
		this.newQuizHandler = newQuizHandler;
		
		// Add the Quiz title
		Label quizLabel = new Label("Quiz");
		quizLabel.getStyleClass().add("quiz-title");
		this.verticalBox.getChildren().add(quizLabel);
		
		// Set the properties
		this.setContent(this.verticalBox);
		this.getStyleClass().add("quiz-container");
		
		// Add the questions to the vertical box.
		addQuestion();
		
	}
	
	/**
	 * Adds all of the questions to the vertical box.
	 */
	private void addQuestion() {
		final int INDEX = this.numQuestionsShowing;
		this.questions.get(this.numQuestionsShowing).addAnsweredHandler(() -> checkAllAnswered(INDEX));
		this.verticalBox.getChildren().add(this.questions.get(this.numQuestionsShowing));
		this.numQuestionsShowing++;
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
		// Check that all the questions have been answered.
		boolean allAnswered = this.questions.size()-1 == index;
		
		// If all have been answered, display the results box
		if(allAnswered) {
			for(int i = 0; i < this.questions.size(); i++) {
				if(this.questions.get(i).answeredCorrectly) {
					this.numCorrect++;
				} else {
					this.numWrong++;
				}
			}
			
			// Place holder until we figure out what it should do.
			EventHandler saveQuizHandler = () -> {
	            ImportExportUtility.master.exportQuiz(this, () -> this.finishHandler.handleEvent());
			};
			
			
			ResultsBox resultsBox = new ResultsBox(this.numCorrect, 
					(this.numWrong + this.numCorrect), 
					this.finishHandler, 
					this.newQuizHandler, 
					saveQuizHandler);
			
			this.verticalBox.getChildren().add(resultsBox);
		} else if(index == this.numQuestionsShowing-1) {
			this.addQuestion();
		}
	}
}

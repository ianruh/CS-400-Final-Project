package application;
import java.util.ArrayList;
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
	private class ResultsBox extends HBox {
		
		/**
		 * Default constructor
		 * @param numCorrect Number of question answered correctly.
		 * @param numWrong Number of questions answered wrong.
		 * @param handler Handler for finishing the quiz.
		 */
		private ResultsBox(int numCorrect, int total, FinishQuizHandler handler) {
			super();
			Label percentageLabel = new Label("Percentage: " + (((double)numCorrect / (double)total)*100) + "%");
			Label numRightWrongLabel = new Label(numCorrect + "/" + total);
			Button finishButton = new Button("Finish");
			
			percentageLabel.getStyleClass().add("finish-quiz-items");
			numRightWrongLabel.getStyleClass().add("finish-quiz-items");
			finishButton.getStyleClass().add("finish-quiz-items");
			
			finishButton.setOnMouseClicked(e -> handler.handleFinish());
			
			this.getChildren().add(percentageLabel);
			this.getChildren().add(numRightWrongLabel);
			this.getChildren().add(finishButton);
		}
	}
	
	// Store the state of the quiz
	private int numCorrect = 0;
	private int numWrong = 0;
	
	// Store a list of the questions.
	ArrayList<BasicQuestion> questions;
	
	// The vertical box used for the layout
	private VBox verticalBox;
	
	// A storage reference for the finish handler
	private FinishQuizHandler finishHandler;
	
	/**
	 * Constructor for a quiz.
	 * @param questions ArrayList of questions to put in the quiz.
	 * @param finishHandler Handler for what to do when the quiz is completed.
	 */
	public BasicQuiz(ArrayList<BasicQuestion> questions, FinishQuizHandler finishHandler) {
		super();
		
		//Initialize the vertical box
		this.verticalBox = new VBox(20);
		
		// Store the questions.
		this.questions = questions;
		
		// Store the handler
		this.finishHandler = finishHandler;
		
		// Add the Quiz title
		Label quizLabel = new Label("Quiz");
		quizLabel.getStyleClass().add("quiz-title");
		this.verticalBox.getChildren().add(quizLabel);
		
		// Set the properties
		this.setContent(this.verticalBox);
		this.getStyleClass().add("quiz-container");
		
		// Add the questions to the vertical box.
		addQuestions();
		
	}
	
	/**
	 * Adds all of the questions to the vertical box.
	 */
	private void addQuestions() {
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).addAnsweredHandler(() -> checkAllAnswered());
			this.verticalBox.getChildren().add(this.questions.get(i));
		}
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
	private void checkAllAnswered() {
		// Check that all the questions have been answered.
		boolean allAnswered = true;
		for(int i = 0; i < this.questions.size(); i++) {
			if(!this.questions.get(i).answered) {
				allAnswered = false;
			}
		}
		
		// If all have been answered, display the results box
		if(allAnswered) {
			for(int i = 0; i < this.questions.size(); i++) {
				if(this.questions.get(i).answeredCorrectly) {
					this.numCorrect++;
				} else {
					this.numWrong++;
				}
			}
			ResultsBox resultsBox = new ResultsBox(this.numCorrect, (this.numWrong + this.numCorrect), this.finishHandler);
			this.verticalBox.getChildren().add(resultsBox);
		}
	}
}

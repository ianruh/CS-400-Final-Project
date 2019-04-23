package application;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BasicQuiz extends ScrollPane implements Quiz {
	
	private class ResultsBox extends HBox {
		
		private ResultsBox(Double percentage, int numCorrect, int numWrong, FinishQuizHandler handler) {
			super();
			Label percentageLabel = new Label("Percentage: " + percentage + "%");
			Label numRightWrongLabel = new Label(numCorrect + "/" + numWrong);
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
	
	private int numCorrect = 0;
	private int numWrong = 0;
	ArrayList<BasicQuestion> questions;
	private VBox verticalBox;
	private FinishQuizHandler finishHandler;
	
	public BasicQuiz(ArrayList<BasicQuestion> questions, FinishQuizHandler finishHandler) {
		super();
		
		this.verticalBox = new VBox(20);
		this.questions = questions;
		Label quizLabel = new Label("Quiz");
		quizLabel.getStyleClass().add("quiz-title");
		this.verticalBox.getChildren().add(quizLabel);
		this.finishHandler = finishHandler;
		addQuestions();
		this.setContent(this.verticalBox);
		this.getStyleClass().add("quiz-container");
		
	}
	
	/**
	 * Adds all of the questions to the vertical box.
	 */
	private void addQuestions() {
		for(int i = 0; i < this.questions.size(); i++) {
			this.questions.get(i).addAnsweredHandler(new QuestionAnsweredHandler() {
				@Override
				public void questionAnswered() {
					checkAllAnswered();
				}
			});
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
		boolean allAnswered = true;
		for(int i = 0; i < this.questions.size(); i++) {
			if(!this.questions.get(i).answered) {
				allAnswered = false;
			}
		}
		if(allAnswered) {
			for(int i = 0; i < this.questions.size(); i++) {
				if(this.questions.get(i).answeredCorrectly) {
					this.numCorrect++;
				} else {
					this.numWrong++;
				}
			}
			ResultsBox resultsBox = new ResultsBox(this.getPercentage(), this.numCorrect, (this.numWrong + this.numCorrect), this.finishHandler);
			this.verticalBox.getChildren().add(resultsBox);
		}
	}
}

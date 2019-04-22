package application;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BasicQuiz extends ScrollPane implements Quiz {
	
	@FunctionalInterface
	private interface FinishHandler {
		public void handleFinish();
	}
	
	private class ResultsBox extends HBox {
		
		private ResultsBox(Double percentage, int numCorrect, int numWrong, FinishHandler handler) {
			super();
			Label percentageLabel = new Label("Percentage: " + percentage);
			Label numRightWongLabel = new Label(numCorrect + "/" + numWrong);
			Button finishButton = new Button("Finish");
			finishButton.setOnMouseClicked(e -> handler.handleFinish());
			
			this.getChildren().add(percentageLabel);
			this.getChildren().add(numRightWongLabel);
			this.getChildren().add(finishButton);
		}
	}
	
	private int numCorrect = 0;
	private int numWrong = 0;
	ArrayList<BasicQuestion> questions;
	ResultsBox resultsBox;
	private VBox verticalBox;
	
	public BasicQuiz(ArrayList<BasicQuestion> questions) {
		super();
		
		this.verticalBox = new VBox(20);
		this.questions = questions;
		Label quizLabel = new Label("Quiz");
		quizLabel.getStyleClass().add("quiz-title");
		this.verticalBox.getChildren().add(quizLabel);
		
		addQuestions();
		this.setContent(this.verticalBox);
		this.getStyleClass().add("quiz-container");
	}
	
	private void addQuestions() {
		for(int i = 0; i < this.questions.size(); i++) {
			this.verticalBox.getChildren().add(this.questions.get(i));
		}
	}

	@Override
	public Double getPercentage() {
		// TODO Auto-generated method stub
		return 0.1;
	}
}

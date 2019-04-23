package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class BasicQuestion extends VBox implements Question {
	
	ArrayList<String> answers;
	int correctAnswer;
	String question;
	String topic;
	String imageSource;
	boolean answered = false;
	boolean answeredCorrectly = false;
	QuestionAnsweredHandler handler = new QuestionAnsweredHandler() {
		@Override
		public void questionAnswered() {
			System.out.println("Question answered handler not set.");
		}
	};
	
	/**
	 * Constructor for a basic question
	 * @param text The text of the question.
	 * @param answers An ArrayList<String> of answers.
	 * @param correctAnswer The index of the correct answer in the list.
	 * @param topic The topic of the question.
	 * @param imageSource The path to the image.
	 */
	public BasicQuestion(String text, ArrayList<String> answers, int correctAnswer, String topic, String imageSource) {
		super(15);
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.question = text;
		this.topic = topic;
		this.imageSource = imageSource;
		addComponents();
		this.getStyleClass().add("question-card");
		this.setMaxWidth(500);
	}
	
	/**
	 * Used to allow each quiz object to add a custom handler to the question.
	 * @param handler to handle when a question is answered.
	 */
	protected void addAnsweredHandler(QuestionAnsweredHandler handler) {
		this.handler = handler;
	}
	
	/**
	 * Constructor for a question with no image.
	 * @param text The text of the question.
	 * @param answers An ArrayList<String> of answers.
	 * @param correctAnswer The index of the correct answer in the list.
	 * @param topic The topic of the question.
	 */
	public BasicQuestion(String text, ArrayList<String> answers, int correctAnswer, String topic) {
		this(text, answers, correctAnswer, topic, null);
	}
	
	private void addComponents() {
		// Question text
		Label questionLabel = new Label(this.question);
		questionLabel.getStyleClass().add("question-text");
		this.getChildren().add(questionLabel);
		
		// Question Topic
		Label topicLabel = new Label(this.topic);
		topicLabel.getStyleClass().add("topic-label");
		this.getChildren().add(topicLabel);
		
		// Image, if there is one.
		if(this.imageSource != null) {
			Image image = new Image(getClass().getResourceAsStream(this.imageSource));
			ImageView imageView = new ImageView(image);
			this.getChildren().add(imageView);
		}
		
		
		for(int i = 0; i < this.answers.size(); i++) {
			final int index = i;
			Answer newAnswer = new Answer(this.answers.get(i), ((i+1) + ". "), new AnswerHandler() {
				@Override
				public void handleClick() {
					if(!answered && index == correctAnswer) {
						answered = true;
						answeredCorrectly = true;
						showAnswerCheckAlert();
					} else if(!answered) {
						answered = true;
						answeredCorrectly = false;
						showAnswerCheckAlert();
					}
					handler.questionAnswered();
				}
				
			});
			this.getChildren().add(newAnswer);
		}
	}
	
	private void showAnswerCheckAlert() {
		Label answerCheckLabel = new Label("The correct answer is: " + this.answers.get(correctAnswer));
		if(this.answeredCorrectly) {
			answerCheckLabel.getStyleClass().add("correct-label");
		} else {
			answerCheckLabel.getStyleClass().add("incorrect-label");
		}
		this.getChildren().add(answerCheckLabel);
	}
	

	@Override
	public ArrayList<String> getAnswers() {
		return this.answers;
	}

}

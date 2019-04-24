package application;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * A BasicQuestion implements the question interface and extends a VBox.
 * @author ianruh
 *
 */
public class BasicQuestion extends VBox implements Question {
	
	// Array list of the answer strings
	private ArrayList<String> answers;
	
	// Index of the correct answer
	private int correctAnswer;
	
	// Text of the question
	private String question;
	
	// The question topic
	private String topic;
	
	// The source of the image for the question if there is one.
	private String imageSource;
	
	// Values to store the state of the question
	protected boolean answered = false;
	protected boolean answeredCorrectly = false;
	
	// Default answer handler
	EventHandler handler = () -> System.out.println("Question answered handler not set.");
	
	/**
	 * Constructor for a basic question
	 * @param text The text of the question.
	 * @param answers An ArrayList<String> of answers.
	 * @param correctAnswer The index of the correct answer in the list.
	 * @param topic The topic of the question.
	 * @param imageSource The path to the image.
	 */
	public BasicQuestion(String text, ArrayList<String> answers, int correctAnswer, String topic, String imageSource) {
		// Set spacing to 15px
		super(15);
		
		// Initialize values
		this.answers = answers;
		this.correctAnswer = correctAnswer;
		this.question = text;
		this.topic = topic;
		this.imageSource = imageSource;
		
		// Set properties
		this.getStyleClass().add("question-card");
		this.setMaxWidth(500);
		
		// Add all of the components
		addComponents();
	}
	
	/**
	 * Used to allow each quiz object to add a custom handler to the question.
	 * @param handler to handle when a question is answered.
	 */
	protected void addAnsweredHandler(EventHandler handler) {
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
	
	/**
	 * Utility method to add all of the components to the node.
	 */
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
		
		// Loop through and add each answer
		for(int i = 0; i < this.answers.size(); i++) {
			// need to use a final in the lambda
			final int index = i;
			Button button = new Button((i+1) + ". " + this.answers.get(i) + "");
			button.setOnMouseClicked(e -> {
				if(!answered && index == correctAnswer) {
					answered = true;
					answeredCorrectly = true;
					showAnswerCheckAlert();
				} else if(!answered) {
					answered = true;
					answeredCorrectly = false;
					showAnswerCheckAlert();
				}
				handler.handleEvent();
			});
			button.getStyleClass().add("answer-button");
			this.getChildren().add(button);
		}
	}
	
	/**
	 * Method is called when a user clicks on an answer. If it is correct, green banner,
	 * if it is wrong, red banner.
	 */
	private void showAnswerCheckAlert() {
		Label answerCheckLabel;
		
		// Set background colors
		if(this.answeredCorrectly) {
			answerCheckLabel = new Label("Hooray!!!");
			answerCheckLabel.getStyleClass().add("correct-label");
		} else {
			answerCheckLabel = new Label("Whoopsies :(");
			answerCheckLabel.getStyleClass().add("incorrect-label");
		}
		
		// Add to the node
		this.getChildren().add(answerCheckLabel);
	}
	
	/**
	 * Method used to get all of the answers from the question.
	 */
	@Override
	public ArrayList<String> getAnswers() {
		return this.answers;
	}

}

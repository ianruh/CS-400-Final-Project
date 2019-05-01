package application;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * A BasicQuestion implements the question interface and extends a VBox.
 *
 */
public class BasicQuestion extends VBox implements Question {
	
	// Array list of the answer strings
	private List<String> answers;
	
	// Index of the correct answer
	private int correctAnswer;
	
	// Text of the question
	private String question;
	
	// The question topic
	protected String topic;
	
	// The source of the image for the question if there is one.
	private String imageSource;
	
	// Values to store the state of the question
	protected boolean answered = false;
	protected boolean answeredCorrectly = false;
	protected Label answerCheckLabel = null;
	
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
	public BasicQuestion(String text, List<String> answers, int correctAnswer, String topic, String imageSource) {
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
		
		// Atempt to load an image from teh given path.
		// If there isn't one, or it doesn't find the image, shows a placeholder.
		try {
			if(this.imageSource != null && !this.imageSource.equals("none")) {
				// Load from image url
				Image image = new Image(this.imageSource);
				ImageView imageView = new ImageView(image);
				imageView.setFitHeight(200);
				imageView.setFitWidth(200);
				imageView.setPreserveRatio(true);
				this.getChildren().add(imageView);
			} else {
				// Failed to load exception
				throw new Exception();
			}
		} catch (Exception e) {
			// No valid image file -- load the default image
			Image image = new Image("place-holder.png");
			ImageView imageView = new ImageView(image);
			imageView.setFitHeight(200);
			imageView.setFitWidth(200);
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
		// Set background colors
		if(this.answeredCorrectly) {
			this.answerCheckLabel = new Label("Hooray!!!");
			this.answerCheckLabel.getStyleClass().add("correct-label");
		} else {
			this.answerCheckLabel = new Label("Whoopsies :(");
			this.answerCheckLabel.getStyleClass().add("incorrect-label");
		}
		
		// Add to the node
		this.getChildren().add(this.answerCheckLabel);
	}
	
	/**
	 * Method used to get all of the answers from the question.
	 */
	@Override
	public List<String> getAnswers() {
		return this.answers;
	}
	
	/**
	 * Method used to determine if two questions are the same.
	 * @param question to check.
	 * @return true or false
	 */
	@Override
	public boolean equals(Object questionObject) {
		if (questionObject == null) {
            return false;
        }

        if (!BasicQuestion.class.isAssignableFrom(questionObject.getClass())) {
            return false;
        }

        final BasicQuestion question = (BasicQuestion) questionObject;
        
		if(this.toString().equals(question.toString()))
			return true;
		return false;
	}
	
	/**
	 * Overridden hash code in order to use the distinct stream method.
	 */
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	/**
	 * To string of the question.
	 * @return String representation.
	 */
	@Override
	public String toString() {
		String out = "";
		out += this.question + "\n";
		out += this.topic + "\n";
		out += this.imageSource + "\n";
		for(String answer: this.answers) {
			out += "["+answer+"]\n";
		}
		out += "The correct answer is: " + this.correctAnswer + "\n";
		return out;
	}
	
	/**
	 * Method used to export the question to JSON.
	 * @return JSON object representing the question.
	 */
	protected JSONObject getJSONObject() {
		// Add all base properties.
		JSONObject map = new JSONObject(); 
		map.put("meta-data", "unused");
		map.put("questionText", this.question);
		map.put("topic", this.topic);
		if(this.imageSource != null) {
			map.put("image", this.imageSource);
		} else {
			map.put("image", "none");
		}
		
		// Add all of the images.
		JSONArray answers = new JSONArray();
		for(int i = 0; i < this.answers.size(); i++) {
			JSONObject answer = new JSONObject();
			if(i == this.correctAnswer) {
				answer.put("isCorrect", "T");
			} else {
				answer.put("isCorrect", "F");
			}
			answer.put("choice", this.answers.get(i));
			answers.add(answer);
		}
		map.put("choiceArray", answers);
		return map;
	}

}

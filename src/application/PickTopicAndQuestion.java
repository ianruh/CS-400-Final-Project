package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PickTopicAndQuestion extends VBox {

	private ObservableList topics;
	private EventHandler finishHandler;
	private String newTopic;
	private String newQuestion;
	private String newAnswer;
	private String imageURL;
	private int count;
	private int correctAnswer;
	private List<String> answers;
	private HashMap<String, ArrayList<BasicQuestion>> table;

	public PickTopicAndQuestion(ObservableList topics, EventHandler finishHandler) {
		// Create vertical box
		super(10);
		this.table = new HashMap<String, ArrayList<BasicQuestion>>();
		this.topics = topics;
		this.count = 0;
		this.correctAnswer = -1;
		this.imageURL = "none";
		this.newTopic = "";
		this.newQuestion = "";
		this.newTopic = "";
		this.newAnswer = "";
		this.answers = new ArrayList<String>();
		this.finishHandler = finishHandler;
		addComponents(topics);
	}

	private void addComponents(ObservableList topics) {
		this.getChildren().clear();

		// Add Question Title
		Label titleLabel = new Label("Follow the Steps to Adding a Question:\n\n");
		titleLabel.setScaleX(2.0);
		titleLabel.setScaleY(2.0);
		titleLabel.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(titleLabel);

		// Big Step 1
		Label step1 = new Label("Step 1:");
		step1.setScaleX(1.5);
		step1.setScaleY(1.5);
		step1.setTextAlignment(TextAlignment.LEFT);
		super.getChildren().add(step1);

		// Topic Instructions Label
		Label pickTopicInstructions = new Label("SELECT A EXISTING topic from the drop down below:\n\n");
		pickTopicInstructions.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(pickTopicInstructions);

		// Combo box for existing topics
		ComboBox<String> comboBox = new ComboBox(topics);
		comboBox.setMinWidth(320);
		super.getChildren().add(comboBox);
		this.newTopic = comboBox.getValue();

		// Big OR
		Label bigORLabel = new Label("OR");
		bigORLabel.setScaleX(1.5);
		bigORLabel.setScaleY(1.5);
		bigORLabel.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(bigORLabel);

		// New custom topic label
		Label addTopicInstructions = new Label(
				"ADD A NEW topic by typing in the new topic name (This will always override the select an existing topic option):\n");
		addTopicInstructions.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(addTopicInstructions);

		// Textbox for new custom question
		TextArea newTopic = new TextArea();
		newTopic.setMaxHeight(25);
		newTopic.setMaxWidth(320);
		super.getChildren().add(newTopic);
		
		// Disable topics dropdown if we're created a new custom topic
		newTopic.setOnKeyReleased(event -> {
			if (newTopic.getText().length() > 0) {
				comboBox.setDisable(true);
			} else {
				comboBox.setDisable(false);
			}
		});

		// Set whether the combobox to unmodifiable if this takes priority

		// add spacing
		Label addSpacing1 = new Label("\n");
		addSpacing1.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(addSpacing1);

		// Big Step 2
		Label step2 = new Label("Step 2:");
		step2.setScaleX(1.5);
		step2.setScaleY(1.5);
		step2.setTextAlignment(TextAlignment.LEFT);
		super.getChildren().add(step2);

		// Question body Label
		Label questionBodyDescription = new Label("Type the question that will be asked:");
		questionBodyDescription.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(questionBodyDescription);

		// Question body textbox
		TextArea questionBody = new TextArea();
		questionBody.setMaxWidth(400);
		questionBody.setMaxHeight(80);
		super.getChildren().add(questionBody);

		// Add spacing
		Label addSpacing2 = new Label("\n");
		addSpacing2.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(addSpacing2);

		// Big Step 3
		Label step3 = new Label("Step 3 (Optional):");
		step3.setScaleX(1.5);
		step3.setScaleY(1.5);
		step3.setTextAlignment(TextAlignment.LEFT);
		super.getChildren().add(step3);

		// Load Image label
		HBox imageBox = new HBox();
		Button b1 = new Button("Load Image");
		b1.setMinWidth(100);
		Label l1 = new Label("Current Path: " + imageURL);
		b1.setOnAction(e -> {
			String imageURLPrevious = imageURL;
			imageURL = ImportExportUtility.master.selectIMGFile();
			if (imageURL == null) {
				imageURL = "none";
			}

			// See if need need to update the GUI
			if (!imageURL.equals(imageURLPrevious)) {
				l1.setText("Current Path: " + imageURL);
				b1.setText("Change Image");
			}
		});
		imageBox.getChildren().add(l1);
		imageBox.getChildren().add(b1);
		imageBox.setSpacing(16);
		imageBox.setAlignment(Pos.CENTER);
		super.getChildren().add(imageBox);

		// Insert Answer HBOX
		HBox insertAnswer = new HBox();

		// add spacing
		Label addSpacing3 = new Label("\n");
		addSpacing3.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(addSpacing3);

		// Big Step 3
		Label step4 = new Label("Step 4:");
		step4.setScaleX(1.5);
		step4.setScaleY(1.5);
		step4.setTextAlignment(TextAlignment.LEFT);
		super.getChildren().add(step4);

		// Insert Answer Label
		Label answerLabel = new Label("Write a possible answer and choose its correctness.");
		answerLabel.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(answerLabel);
		// Insert Answer - Text prompt
		TextArea answerBody = new TextArea();
		answerBody.setMaxWidth(280);
		answerBody.setMaxHeight(25);
		insertAnswer.getChildren().add(answerBody);
		// this.newAnswer = answerBody.getText();

		// Insert Answer - True/False
		ObservableList<String> trueOrFalse = FXCollections.observableArrayList("True", "False");
		ComboBox<String> correctness = new ComboBox(trueOrFalse);
		insertAnswer.getChildren().add(correctness);

		// Insert Answer - Add Button
		Button insertButton = new Button("Insert Answer");
		insertButton.setMinWidth(100);
		insertAnswer.getChildren().add(insertButton);

		// insert Button clicked
		insertButton.setOnMouseClicked(e -> {
			
			// Handle Answer Creation
			if (answerBody.getText() != null && answerBody.getText().length() > 0) {
				this.answers.add(answerBody.getText().trim());
			}
			answerBody.clear();

			// Determine what type of answer was inserted
			if (correctness.getValue().compareTo("True") == 0) {
				this.correctAnswer = this.count;
			} else {
				this.count++;
			}
			correctness.valueProperty().set(null);
			
		});

		// Add Answer to super
		insertAnswer.setAlignment(Pos.CENTER);
		insertAnswer.setSpacing(5);
		super.getChildren().add(insertAnswer);

		// Blank space
		Label blankSpace = new Label(" ");
		blankSpace.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(blankSpace);

		// Create a button to submit question
		Button submitQuestion = new Button("Submit Question");
		submitQuestion.setMinWidth(100);
		submitQuestion.setOnMouseClicked(e -> {
			
			// Pull the question body string
			newQuestion = questionBody.getText();
			
			// Update topic
			if (newTopic.getText() != null && newTopic.getText().length() > 0) {
				this.newTopic = newTopic.getText().trim();
			} else {
				this.newTopic = comboBox.getValue();
			}
			
			// Test if all the required fields are met
			if (this.newQuestion != null && this.answers.size() > 0 && this.correctAnswer != -1 &&
				this.newTopic != null) {
				
				// If so, create new question
				QuestionBank.master.addQuestion(new BasicQuestion((String) this.newQuestion, this.answers,
												this.correctAnswer, (String) this.newTopic, (String) this.imageURL));

				// Return to menu
				this.finishHandler.handleEvent();
			} else {
				// Show alert! 
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Uh oh!");
		    	alert.setHeaderText("Unable to create new question!");
		    	alert.setContentText("You must fill in all the necessary information before you can submit a" +
		    						" new question. \n\nYou must have:\n\t- A topic\n\t- At least 1 correct answer.");
		    	Optional<ButtonType> result = alert.showAndWait(); 
			}
		});
		submitQuestion.setMinSize(256, 48);
		super.getChildren().add(submitQuestion);

		// Create a button to cancel "operations"
		Button cancelButton = new Button("Cancel");
		cancelButton.setMinWidth(100);
		cancelButton.setOnMouseClicked(e -> this.finishHandler.handleEvent());
		cancelButton.setMinSize(256, 25);
		super.getChildren().add(cancelButton);
		super.setAlignment(Pos.CENTER);
	}
}

package application;
	
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class StartQuiz extends VBox {
	
	private EventHandler finishHandler;
	
	public StartQuiz(EventHandler finishHandler) {
		super();
		this.finishHandler = finishHandler;
		this.addComponents();
	}

	public void addComponents() {

		// Return VBox
		VBox mainVBox = this;
		mainVBox.setAlignment(Pos.CENTER);
		mainVBox.setSpacing(16);
		
	    // Add Title
	    Label titleLabel = new Label("Follow the Steps to Create a Quiz:\n\n");
	    titleLabel.setScaleX(2.0);
	    titleLabel.setScaleY(2.0);
	    titleLabel.setTextAlignment(TextAlignment.CENTER);
	    mainVBox.getChildren().add(titleLabel);
	    
		// Pull  Topics List
		List<String> topics = QuestionBank.master.getTopics();
		topics.sort(String.CASE_INSENSITIVE_ORDER);
		
	    // "Step 1" Title
	    Label step1 = new Label("Step 1:");
	    step1.setScaleX(1.5);
	    step1.setScaleY(1.5);
	    step1.setTextAlignment(TextAlignment.LEFT);
	    mainVBox.getChildren().add(step1);
	    
	    // Topics Label
		Label topicsLabel = new Label("Please select the topics you would like to be quized on:\n");
		mainVBox.getChildren().add(topicsLabel);
		
		// Create Topic List Scroll Field
		VBox topicList = new VBox();
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxHeight(96);
		scrollPane.setMaxWidth(256);
		
		// Pull topics from database
		for (int i = 0; i < topics.size(); i++) {
		  CheckBox topicBox = new CheckBox(topics.get(i));
		  topicBox.setAlignment(Pos.CENTER);
		  topicList.getChildren().add(topicBox);
		  topicBox.setSelected(false);
		}
		scrollPane.setContent(topicList);
		mainVBox.getChildren().add(scrollPane);

		// Numbers-only filter for # questions prompt
		UnaryOperator<Change> numFilter = change -> {
			String text = change.getText();

			if (text.matches("[0-9]*")) {
				return change;
			}

			return null;
		};

	    // Spacing
	    Label addSpacing1 =
	        new Label("\n");
	    addSpacing1.setTextAlignment(TextAlignment.CENTER);
	    mainVBox.getChildren().add(addSpacing1);
		
	    // "Step 2:" Title
        Label step2 = new Label("Step 2:");
        step2.setScaleX(1.5);
        step2.setScaleY(1.5);
        step2.setTextAlignment(TextAlignment.LEFT);
        mainVBox.getChildren().add(step2);
		
		// Number of questions prompt
		Label questionsLabel = new Label("Please enter the number of questions you want in this quiz:");
		TextFormatter<String> format = new TextFormatter<>(numFilter);
		TextField questionsField = new TextField();
		questionsField.setTextFormatter(format);
		HBox hbQuestions = new HBox();
		hbQuestions.getChildren().addAll(questionsLabel, questionsField);
		hbQuestions.setSpacing(10);
		hbQuestions.setAlignment(Pos.CENTER);
		mainVBox.getChildren().add(hbQuestions);
		
	    // Spacing
	    Label addSpacing2 =
	        new Label("\n");
	    addSpacing1.setTextAlignment(TextAlignment.CENTER);
	    mainVBox.getChildren().add(addSpacing2);
		
		// Begin Button
		Button beginButton = new Button("Begin Quiz");
		beginButton.setMinSize(256, 48);
		beginButton.setOnMouseClicked(e -> {
			// Get the selected topics
			ArrayList<String> selectedTopics = new ArrayList<String>();
			for(Node box: topicList.getChildren()) {
				CheckBox checkBox = (CheckBox)box;
				if(checkBox.isSelected()) {
					selectedTopics.add(checkBox.getText());
				}
			}
			
			// If there are selected topics, begin the quiz
			if(selectedTopics.size() != 0 && !questionsField.getText().equals("")) {
				// Get the number of questions
				int numQuestions = (int)Double.parseDouble(questionsField.getText());
				
				// Make the quiz
				BasicQuiz quiz = new BasicQuiz(QuestionBank.master.getNewQuiz(numQuestions, selectedTopics), finishHandler, () -> {
					this.getChildren().clear();
					this.getChildren().add(new StartQuiz(finishHandler));
				});
				this.getChildren().clear();
				this.getChildren().add(quiz);
			} else {
				// Show alert! 
		    	Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Uh oh!");
		    	alert.setHeaderText("Unable to start the quiz!");
		    	alert.setContentText("You must fill in all the necessary information before you can start a" +
		    						" new quiz. \n\nYou must have:\n\t- Selected a topic(s)\n\t- Entered the desired number of questions.");
		    	Optional<ButtonType> result = alert.showAndWait(); 
			}
		});
		beginButton.setPrefWidth(156);
		mainVBox.getChildren().add(beginButton);
		
		// Back Button
		Button backButton = new Button("Back");
		backButton.setMinSize(256, 25);
		backButton.setOnMouseClicked(e -> this.finishHandler.handleEvent());
		backButton.setPrefWidth(156);
		mainVBox.getChildren().add(backButton);
	}
}


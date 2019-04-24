package application;
	

import java.util.function.UnaryOperator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartQuiz extends Main {
	
	private EventHandler finishHandler;
	private EventHandler beginHandler;
	
	public StartQuiz(EventHandler beginHandler, EventHandler finishHandler) {
		super();
		this.finishHandler = finishHandler;
		this.beginHandler = beginHandler;
	}

	public VBox returnScene() {

		// Return VBox
		VBox mainVBox = new VBox();
		mainVBox.setAlignment(Pos.CENTER);
		mainVBox.setSpacing(16);
		
		// Title
		Label title = new Label("Quiz Options:\n");
		title.setScaleX(2.0);
		title.setScaleY(2.0);
		mainVBox.getChildren().add(title);
		
		// Topics
		String[] topics = {"Topic 1", "Topic 2", "Topic 3", 
				"Topic 4", "Topic 5", "Topic 6", "Topic 7", "Topic 8", 
				"Topic 9", "Topic 10", };
	     
	    // Topics Label
		Label topicsLabel = new Label("1) Please select the topics you would like to be quized on:\n");
		mainVBox.getChildren().add(topicsLabel);
		
		// Create Topics Scroll List
		VBox topicList = new VBox();
		topicList.setAlignment(Pos.CENTER);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setMaxHeight(96);
		scrollPane.setMaxWidth(256);
		//scrollPane.setPrefSize(256, 96);	
		
		for (int i = 0; i < topics.length; i++) {
		  CheckBox topicBox = new CheckBox(topics[i]);
		  topicBox.setAlignment(Pos.CENTER);
		  topicList.getChildren().add(topicBox);
		  topicBox.setIndeterminate(true);
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

		// Number of questions prompt
		Label questionsLabel = new Label("2) Please enter the number of questions you want in this quiz:");
		TextFormatter<String> format = new TextFormatter<>(numFilter);
		TextField questionsField = new TextField();
		questionsField.setTextFormatter(format);
		HBox hbQuestions = new HBox();
		hbQuestions.getChildren().addAll(questionsLabel, questionsField);
		hbQuestions.setSpacing(10);
		hbQuestions.setAlignment(Pos.CENTER);
		mainVBox.getChildren().add(hbQuestions);
		
		// Begin Button
		Button beginButton = new Button("Begin Quiz");
		beginButton.setOnMouseClicked(e -> this.beginHandler.handleEvent());
		beginButton.setPrefWidth(156);
		mainVBox.getChildren().add(beginButton);
		
		// Back Button
		Button backButton = new Button("Back");
		backButton.setOnMouseClicked(e -> this.finishHandler.handleEvent());
		backButton.setPrefWidth(156);
		mainVBox.getChildren().add(backButton);
		
		// Return scene
		return mainVBox;
	}
}


package application;
	

import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartQuiz extends Main {

	public VBox returnScene() {

		// Title
		Label title = new Label("Quiz Options:");

		// Topics List
		ObservableList<String> topics = FXCollections.observableArrayList("Topic 1", "Topic 2", "Topic 3", "Topic 4",
				"Topic 5");

		// Topics drop-down
		Label topicsLabel = new Label("Topic:");
		ComboBox topicBox = new ComboBox(topics);
		HBox hbTopics = new HBox();
		hbTopics.getChildren().addAll(topicsLabel, topicBox);
		hbTopics.setSpacing(10);
		hbTopics.setAlignment(Pos.CENTER);

		// Numbers-only filter for # questions prompt
		UnaryOperator<Change> numFilter = change -> {
			String text = change.getText();

			if (text.matches("[0-9]*")) {
				return change;
			}

			return null;
		};

		// Number of questions prompt
		Label questionsLabel = new Label("Number of questions:");
		TextFormatter<String> format = new TextFormatter<>(numFilter);
		TextField questionsField = new TextField();
		questionsField.setTextFormatter(format);
		HBox hbQuestions = new HBox();
		hbQuestions.getChildren().addAll(questionsLabel, questionsField);
		hbQuestions.setSpacing(10);
		hbQuestions.setAlignment(Pos.CENTER);

		// Begin Button
		Button beginButton = new Button("Begin Quiz");
		beginButton.setPrefWidth(156);

		// Back Button
		Button backButton = new Button("Back");
		backButton.setPrefWidth(156);

		// Stack Pane
		VBox mainPane = new VBox();
		mainPane.getChildren().addAll(title, hbTopics, hbQuestions, beginButton, backButton);
		mainPane.setSpacing(32);
		mainPane.setAlignment(Pos.CENTER);

		// Return scene
		return mainPane;
	}
}


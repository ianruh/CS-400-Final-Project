package application;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class Answer extends HBox {
	/**
	 * A constructor for an answer.
	 * @param text The text of the asnwer.
	 * @param label The label e.g. 'A' or '1.'
	 * @param handler The handler for a click event.
	 */
	public Answer(String text, String label, AnswerHandler handler) {
		super();
		
		Button button = new Button(label + text + "");
		button.setOnMouseClicked(e -> handler.handleClick());
		button.getStyleClass().add("answer-button");
		this.getChildren().add(button);
	}
}

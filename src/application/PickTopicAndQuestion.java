package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PickTopicAndQuestion extends VBox {
  ObservableList<String> trueOrFalse =
      FXCollections.observableArrayList("True", "False");
  public PickTopicAndQuestion(ObservableList topics) {
    super(10);
    
    ComboBox comboBox = new ComboBox(topics);
    super.getChildren().add(comboBox);
    
    TextArea newTopic = new TextArea();
    super.getChildren().add(newTopic);
    
    Button b0 = new Button("Add Question");
    super.getChildren().add(b0);
    
    Button b1 = new Button("Load Image");
    super.getChildren().add(b1);
    
    Button b2 = new Button("Edit Answers");
    super.getChildren().add(b2);
    
    Button b3 = new Button("Cancel");
    super.getChildren().add(b3);
  }
}

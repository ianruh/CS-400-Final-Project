package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PickTopicAndQuestion extends VBox {

  public PickTopicAndQuestion(ObservableList topics) {
    // creates my vertical box
    super(10);
    
    // creates a combo box that holds all current topics
    ComboBox<String> comboBox = new ComboBox(topics);
    super.getChildren().add(comboBox);
    
    // creates a text box where users can enter new topics
    TextArea newTopic = new TextArea();
    newTopic.setMaxHeight(50);
    newTopic.setMaxWidth(150);
    super.getChildren().add(newTopic);
    
    // explain what the text area is for
    Label b = new Label("Type in box above and press enter to add a topic");
    b.setMinWidth(100);
    super.getChildren().add(b);
    
    // create a button to add a question
    Button b0 = new Button("Add Question");
    b0.setMinWidth(100);
    super.getChildren().add(b0);
    
    // create a button to load an image
    Button b1 = new Button("Load Image");
    b1.setMinWidth(100);
    super.getChildren().add(b1);
    b1.setOnMouseClicked( -> new AddQuestion());
    
    // create a button the edit ansers
    Button b2 = new Button("Edit Answers");
    b2.setMinWidth(100);
    super.getChildren().add(b2);
    
    // create a button to cancel "operations"
    Button b3 = new Button("Cancel");
    b3.setMinWidth(100);
    super.getChildren().add(b3);
  }
}

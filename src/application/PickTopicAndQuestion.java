package application;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class PickTopicAndQuestion extends VBox {

  private ObservableList topics;
  private EventHandler finishHandler;
  
  
  public PickTopicAndQuestion(ObservableList topics, EventHandler finishHandler) {
    // creates my vertical box
    super(10);
    this.topics = topics;
    this.finishHandler = finishHandler;
    addComponents(topics);
  }
  
  private void addComponents(ObservableList topics) {
    this.getChildren().clear();
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
    b0.setOnMouseClicked( event -> addQuestion());
    
    // create a button to load an image
    Button b1 = new Button("Load Image");
    b1.setMinWidth(100);
//    b1.setOnMouseClicked(/* go to save / load screen*/);
    super.getChildren().add(b1);
    
    
    // create a button the edit ansers
    Button b2 = new Button("Edit Answers");
    b2.setMinWidth(100);
//    b2.setOnMouseClicked(/* some how edit answers*/);
    super.getChildren().add(b2);
    
    // create a button to cancel "operations"
    Button b3 = new Button("Cancel");
    b3.setMinWidth(100);
    b3.setOnMouseClicked(e -> this.finishHandler.handleEvent());
    super.getChildren().add(b3);
  }
  
  // method to add a question and come back to this page once the question has been added
  private void addQuestion() {
    this.getChildren().clear();
    this.getChildren().add(new AddQuestion(new AddQuestionInterface() {
      @Override
      public void handleBack() {
        addComponents(topics);
      }

    }));
  }
  
}

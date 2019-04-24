package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

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
    
    // Add Question Title
    Label titleLabel = new Label("Add Question:");
    titleLabel.setScaleX(2.0);
    titleLabel.setScaleY(2.0);
    titleLabel.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(titleLabel);
    
    // Topic Instructions Label
    Label pickTopicInstructions =
        new Label("SELECT A EXISTING topic from the drop down below:\n\n");
    pickTopicInstructions.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(pickTopicInstructions);
    
    // Combo box for existing topics
    ComboBox<String> comboBox = new ComboBox(topics);
    comboBox.setMinWidth(320);
    super.getChildren().add(comboBox);
    
    // Big OR
    Label bigORLabel = new Label("OR");
    bigORLabel.setScaleX(1.5);
    bigORLabel.setScaleY(1.5);
    bigORLabel.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(bigORLabel);
    
    // New custom topic label
    Label addTopicInstructions =
        new Label("ADD A NEW topic by typing in the new topic name:\n");
    addTopicInstructions.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(addTopicInstructions);
    
    // Textbox for neww custom question
    TextArea newTopic = new TextArea();
    newTopic.setMaxHeight(25);
    newTopic.setMaxWidth(320);
    super.getChildren().add(newTopic);
    
    // Question body Label
    Label questionBodyDescription = new Label("Type the question that will be asked:");
    questionBodyDescription.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(questionBodyDescription);
    
    // Question body textbox 
    TextArea questionBody = new TextArea();
    questionBody.setMaxWidth(400);
    questionBody.setMaxHeight(80);
    super.getChildren().add(questionBody);
    
    // Load Image label
    Label imageLabel = new Label("\nLoad an image you would like to display (optional):");
    imageLabel.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(imageLabel);
    
    // Load Image Button
    Button b1 = new Button("Load Image");
    b1.setMinWidth(100);
    super.getChildren().add(b1);
    
    // Insert Answer HBOX
    HBox insertAnswer = new HBox();
    
    // Insert Answer Label
    Label answerLabel = new Label("\nWrite a possible answer and choose its correctness.");
    answerLabel.setTextAlignment(TextAlignment.CENTER);
    super.getChildren().add(answerLabel);
    
    //super.getChildren().add(answerLabel);
 
    // Insert Answer - Text prompt
    TextArea answerBody = new TextArea();
    answerBody.setMaxWidth(280);
    answerBody.setMaxHeight(25);
    insertAnswer.getChildren().add(answerBody);    
    
    // Insert Answer - True/False
    ObservableList<String> trueOrFalse =
    	      FXCollections.observableArrayList("True", "False");
    ComboBox<String> correctness = new ComboBox(trueOrFalse);
    insertAnswer.getChildren().add(correctness);
   
    // Insert Answer - Add Button
    Button insertButton = new Button("Insert Answer");
    insertButton.setMinWidth(100);
    insertAnswer.getChildren().add(insertButton);

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
    submitQuestion.setOnMouseClicked(e -> this.finishHandler.handleEvent());
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

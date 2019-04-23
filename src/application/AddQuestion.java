package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddQuestion extends VBox{

  ObservableList<String> trueOrFalse =
      FXCollections.observableArrayList("True", "False");
  
  public AddQuestion(AddQuestionInterface backHandler) {
    // creates our vertical box
    super(10); 
    
    // adds a text field first
    TextArea textA1 = new TextArea();
    super.getChildren().add(textA1);
    
    // then add a combo box below it with 2 options
    ComboBox<String> ComboB1 = new ComboBox<String>(trueOrFalse);
    super.getChildren().add(ComboB1);
   
    // creates a horizontal box that will display all inserted questions
    HBox hbox = new HBox(10);
    super.getChildren().add(hbox);
    TextArea textA2 = new TextArea();
    textA2.setEditable(false);
    ScrollPane scroll = new ScrollPane();
    scroll.setContent(textA2);
    hbox.getChildren().add(scroll);
    
    // creates 3rd to bottom butotn
    Button b1 = new Button("Insert Answer");
    super.getChildren().add(b1);
    
    // creates 2nd to bottom button
    Button b2 = new Button("Submit Question");
    b2.setOnMouseClicked(e -> /* this should go back to main menu*/);
    super.getChildren().add(b2);
    
    // creates bottom button
    Button b3 = new Button("Back");
    b3.setOnMouseClicked(e -> backHandler.handleBack());
    super.getChildren().add(b3);
 
  }  

}

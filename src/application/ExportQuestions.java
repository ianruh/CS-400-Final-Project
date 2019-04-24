package application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class ExportQuestions extends BorderPane{
  private EventHandler finishHandler;
  
  public ExportQuestions(EventHandler finishHandler) {
    this.finishHandler = finishHandler;
    
    
    // create an load prompt asking to input the file name
    Label exportPrompt =
        new Label("Save: "
            + "\n Please enter the name and location of the JSON "
            + "\n file of questions you would like to export"
            + "\n \n example: Users/Computer/Desktop/NeedToStudyFrench.json");
    exportPrompt.setTextAlignment(TextAlignment.CENTER);
    TextArea fileName = new TextArea();
    Button buttonSave = new Button("Save");
    Button buttonCancel = new Button("Cancel");      
   
    VBox vbox = new VBox(exportPrompt, fileName, buttonSave, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
    
    buttonCancel.setOnMouseClicked(e -> this.finishHandler.handleEvent());
    
    //need to implement a load button
    //need to implement read in a file name 
  }
  
}

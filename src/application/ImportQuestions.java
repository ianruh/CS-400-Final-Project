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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class ImportQuestions extends BorderPane{
  private EventHandler finishHandler;
  
  public ImportQuestions(EventHandler finishHandler) {
    this.finishHandler = finishHandler;
    
    
    // create an load prompt asking to input the file name
    Label loadPrompt =
        new Label("Load: "
            + "\n Please enter the name of the JSON "
            + "\n file of questions you would like to import");
    //ption to bold the label text
    //loadPrompt.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
    loadPrompt.setTextAlignment(TextAlignment.CENTER);
    TextArea fileName = new TextArea();
    Button buttonLoad = new Button("Import");
    Button buttonCancel = new Button("Cancel");      
   
    VBox vbox = new VBox(loadPrompt, fileName, buttonLoad, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
    
    buttonCancel.setOnMouseClicked(e -> this.finishHandler.handleEvent());
    
    //need to implement a load button
    //need to implement read in a file name 
  }
  
}

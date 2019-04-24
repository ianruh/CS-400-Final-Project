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


public class ExitAndSaveMenu extends BorderPane {

  private EventHandler finishHandler;
  

  
  public ExitAndSaveMenu(EventHandler finishHandler) {
    this.finishHandler = finishHandler;

    
    // create an exit prompt asking to save
    Label exitPrompt =
        new Label("Exit: "
            + "\n Do you want to save your questions?");
    exitPrompt.setTextAlignment(TextAlignment.CENTER);
    Button buttonExitAndSave = new Button("Save & Exit");
    Button buttonExitNoSave = new Button("Exit without Saving");
    Button buttonCancel = new Button("Cancel");      
   
    VBox vbox = new VBox(exitPrompt, buttonExitAndSave, buttonExitNoSave, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
    
    buttonCancel.setOnMouseClicked(e -> this.finishHandler.handleEvent());

    // need to implement closing with outsaving 
    // buttonExitNoSave.setOnMouseClicked(); 
    
    // need to implement closing with saving 
    

  }

  
}

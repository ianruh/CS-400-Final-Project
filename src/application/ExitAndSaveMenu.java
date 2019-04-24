package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;


public class ExitAndSaveMenu extends BorderPane {

  private EventHandler cancelHandler;
  

  
  public ExitAndSaveMenu(EventHandler cancelHandler, Stage primaryStage) {
    this.cancelHandler = cancelHandler;

    
    // create an exit prompt asking to save
    Label exitPrompt =
        new Label("Exit: "
            + "\n Do you want to save your questions?");
    exitPrompt.setTextAlignment(TextAlignment.CENTER);
    Button buttonExitAndSave = new Button("Save & Exit");
    
    buttonExitAndSave.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
    	  
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
            fileChooser.getExtensionFilters().add(extFilter);
            
            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);
            
            if(file != null){
                SaveFile("Example", file);
                Platform.exit();
                System.exit(0);
            }
        }
    });
    
    Button buttonExitNoSave = new Button("Exit without Saving");
    
    buttonExitNoSave.setOnMouseClicked(e -> {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Exit Confirmation");
    	alert.setHeaderText("Are you sure you don't want to save? ");
    	alert.setContentText("All current work will be lost.");
    	Optional<ButtonType> result = alert.showAndWait(); 
    	if(result.isPresent() && result.get() == ButtonType.OK) {
    		Platform.exit();
        	System.exit(0);
    	}
    });
    
    Button buttonCancel = new Button("Cancel");  
    buttonCancel.setOnMouseClicked(e -> this.cancelHandler.handleEvent());
   
    VBox vbox = new VBox(exitPrompt, buttonExitAndSave, buttonExitNoSave, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
  }
  
  private void SaveFile(String content, File file){
      try {
          FileWriter fileWriter = null;
           
          fileWriter = new FileWriter(file);
          fileWriter.write(content);
          fileWriter.close();
      } catch (IOException ex) {
          ex.printStackTrace();
      }
       
  }

  
}

package application;

import java.util.Optional;

import javafx.application.Platform;
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
  

  
  public ExitAndSaveMenu(EventHandler cancelHandler) {
    this.cancelHandler = cancelHandler;

    
    // Exit Title
    Label exitTitle = new Label("Exiting Application\n ");
    exitTitle.setScaleX(3.0);
    exitTitle.setScaleY(3.0);
    exitTitle.setAlignment(Pos.CENTER);
    
    
    // Exit prompt
    Label exitPrompt =
        new Label("Do you want to save your questions?");
    exitPrompt.setScaleX(1.5);
    exitPrompt.setScaleY(1.5);
    exitPrompt.setTextAlignment(TextAlignment.CENTER);
    
    // Save & Exit Button
    Button buttonExitAndSave = new Button("Save & Exit");
    buttonExitAndSave.setPrefSize(256, 56);
    
    buttonExitAndSave.setOnAction((event) -> {
    	EventHandler exitHandler = () -> {
        	Platform.exit();
            System.exit(0);
        };
        ImportExportUtility.saveDialogue("Example", exitHandler);
    });
    
    // Exit w/o Saving Button
    Button buttonExitNoSave = new Button("Exit without Saving");
    buttonExitNoSave.setPrefSize(256, 56);
    
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
    
    // Cancel Button
    Button buttonCancel = new Button("Cancel");  
    buttonCancel.setPrefSize(256, 56);
    
    buttonCancel.setOnMouseClicked(e -> this.cancelHandler.handleEvent());
   
    VBox vbox = new VBox(exitTitle, exitPrompt, buttonExitAndSave, buttonExitNoSave, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
  }
  
}

/**
 * Final Project. Quiz Generator.
 *
 * Filename:   ExitAndSaveMenu
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * @author Preston Lewis, ID 9074531329, prlewis@wisc.edu, lecture 004      
 * @author Jared Krahn, ID 9076949693, jkrahn2@wisc.edu, lecture 004
 * @author Ian Ruh, ID 9080231591, iruh@wisc.edu, lecture 004
 * @author Emily Binversie ID 9063469945, eebinversie@wisc.edu, lecture 004
 *
 * Due Date:   05/02/2019 at 10pm
 * 
 */
package application;

import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

<<<<<<< HEAD
/**
 * ExitAndSaveMenu extends a BorderPane and will be the GUI for users to exit the program.
=======

/**
 * Class the shows the exiting and saving page
 * @author ianruh
>>>>>>> 4d8d6be0cb58eb83a035ef5df91a4c558dae597d
 *
 */
public class ExitAndSaveMenu extends BorderPane {

<<<<<<< HEAD
  
=======
  // Handler for what to do if the user wants to cancel.
>>>>>>> 4d8d6be0cb58eb83a035ef5df91a4c558dae597d
  private EventHandler cancelHandler;
  

  /**
   * Default constructor for the page.
   * @param cancelHandler
   */
  public ExitAndSaveMenu(EventHandler cancelHandler) {
	// Save our cancel handler
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
    
    // Add handler to the save and exit button
    buttonExitAndSave.setOnAction((event) -> {
    	EventHandler exitHandler = () -> {
        	Platform.exit();
            System.exit(0);
        };
        ImportExportUtility.master.exportQuestionBank(exitHandler);
    });
    
    // Exit w/o Saving Button
    Button buttonExitNoSave = new Button("Exit without Saving");
    buttonExitNoSave.setPrefSize(256, 56);
    
    // Add handler to the exit and no save button.
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
    
    // Attach our handler to the cancel button
    buttonCancel.setOnMouseClicked(e -> this.cancelHandler.handleEvent());
   
    // Create out main container
    VBox vbox = new VBox(exitTitle, exitPrompt, buttonExitAndSave, buttonExitNoSave, buttonCancel);
    vbox.setAlignment(Pos.CENTER);
    vbox.setSpacing(40);
    this.setCenter(vbox);
  }
  
}

package application;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class LoadImage extends BorderPane{

  EventHandler finishHandler;
  
  public LoadImage( EventHandler finishHandler) {
    // creates my vertical box
    //super();
    this.finishHandler = finishHandler;
  
  // Load title
  Label loadTitle = new Label("Load Picture");
  loadTitle.setScaleX(2.75);
  loadTitle.setScaleY(2.75);
  loadTitle.setAlignment(Pos.CENTER);

  
  // Create an load prompt asking to input the file name
  Label loadPrompt =
      new Label("\nPlease enter the url of the "
          + "\n file of iamge you would like to import");
  loadPrompt.setScaleX(1.5);
  loadPrompt.setScaleY(1.5);
  loadPrompt.setTextAlignment(TextAlignment.CENTER);
 
  // Filename Textbox
  TextArea fileName = new TextArea();
  fileName.setMaxSize(512, 48);
  
  // Load Button
  Button buttonLoad = new Button("Import File");
  buttonLoad.setPrefSize(256, 48);
  
  // Cancel Button
  Button buttonCancel = new Button("Cancel");      
  buttonCancel.setPrefSize(256, 36);
  
  // Generate returned VBOX
  VBox vbox = new VBox(loadTitle, loadPrompt, fileName, buttonLoad, buttonCancel);
  vbox.setAlignment(Pos.CENTER);
  vbox.setSpacing(40);
  this.setCenter(vbox);
  
  // Cancel button interact
  buttonCancel.setOnMouseClicked(e -> this.finishHandler.handleEvent());
  
  //need to implement a load button
  //need to implement read in a file name 
}
}

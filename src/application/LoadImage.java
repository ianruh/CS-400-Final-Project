package application;

import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;

public class LoadImage extends VBox{

  EventHandler finishHandler;
  
  public LoadImage( EventHandler finishHandler) {
    // creates my vertical box
    super();
    this.finishHandler = finishHandler;
  }
  
  private void addComponents() {
    
  }
}

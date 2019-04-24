package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.application.Platform;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ImportExportUtility {
	protected static Stage primaryStage;
	
	public ImportExportUtility(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private static void SaveFile(String content, File file){
	      try {
	          FileWriter fileWriter = null;
	           
	          fileWriter = new FileWriter(file);
	          fileWriter.write(content);
	          fileWriter.close();
	      } catch (IOException ex) {
	          ex.printStackTrace();
	      }
	       
	  }
	
	protected static void saveDialogue(String text, EventHandler handler) {
		FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(primaryStage);
        
        if(file != null){
            SaveFile(text, file);
            handler.handleEvent();
        }
	}
}
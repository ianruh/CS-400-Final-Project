package application;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * A utility class to import and export json objects.
 * @author ianruh
 *
 */
public class ImportExportUtility {
	// The stage used to display the popups
	protected Stage primaryStage;
	
	// The singleton instance with the stage
	public static ImportExportUtility master;
	
	/**
	 * Constructor that should only be called once.
	 * @param primaryStage
	 */
	public ImportExportUtility(Stage primaryStage) {
		this.primaryStage = primaryStage;
		ImportExportUtility.master = this;
	}
	
	private void saveFile(String content, File file){
	      try {
	          FileWriter fileWriter = null;
	           
	          fileWriter = new FileWriter(file);
	          fileWriter.write(content);
	          fileWriter.close();
	      } catch (IOException ex) {
	          ex.printStackTrace();
	      }
	       
	  }
	
	protected void saveDialogue(String text, EventHandler handler) {
		FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show save file dialog
        File file = fileChooser.showSaveDialog(this.primaryStage);
        
        if(file != null){
            saveFile(text, file);
            handler.handleEvent();
        }
	}
	
	/**
	 * Shows a dialogue to choose a file.
	 * @return the file object, or null if non selected.
	 */
	protected File selectJSONFile() {
		FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Open Resource File");
		 fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
		 return fileChooser.showOpenDialog(primaryStage);
	}
	
	/**
	 * This method takes a file object, and attempts to parse it as a JSON file containing
	 * questions of the correct format.
	 * @param file The file to be parsed.
	 * @return a list of the questions.
	 */
	protected List<BasicQuestion> importQuestions(File file) {
		ArrayList<BasicQuestion> questions = new ArrayList<BasicQuestion>();
		try {
			// parsing file "JSONExample.json" 
	        Object object = new JSONParser().parse(new FileReader(file)); 
	          
	        // typecasting obj to JSONObject 
	        JSONObject jsonObject = (JSONObject) object; 
	        
	        // getting phoneNumbers 
	        JSONArray questionJSONArray = (JSONArray) jsonObject.get("questionArray"); 
	        
	        for(Object questionObject: questionJSONArray) {
	        	JSONObject question = (JSONObject) questionObject;
	        	String questionText = (String)question.get("questionText");
	        	String topic = (String)question.get("topic");
	        	String image = (String)question.get("image");
	        	ArrayList<String> answers = new ArrayList<String>();
	        	JSONArray answerArray = (JSONArray) question.get("choiceArray");
	        	int index = 0;
	        	int correctIndex = -1;
	        	for(Object choiceObject: answerArray) {
	        		JSONObject choice = (JSONObject) choiceObject;
	        		String answerText = (String)choice.get("choice");
	        		answers.add(answerText);
	        		if(((String)choice.get("isCorrect")).equals("T")) {
	        			correctIndex = index;
	        		}
	        		index++;
	        	}
	        	if(correctIndex == -1) {
	        		throw new RuntimeException("No answers were correct.");
	        	}
	        	if(image.equals("none"))
	        		image = null;
	        	BasicQuestion newQuestion = new BasicQuestion(questionText, answers, correctIndex, topic, image);
	        	questions.add(newQuestion);
	        }
	        
		} catch(Exception e) {
			System.out.println("Exception thrown while attempting to parse json file.");
			e.printStackTrace();
		}
		return questions;
	}
	
	/**
	 * Exporter for a quiz.
	 * @param quiz Quiz to export.
	 * @param finishHandler Event handler for what to do when done.
	 */
	protected void exportQuiz(BasicQuiz quiz, EventHandler finishHandler) {
		JSONArray quizObject = new JSONArray();
		for(BasicQuestion question: quiz.questions) {
			quizObject.add(question.getJSONObject());
		}
		JSONObject questionSet = new JSONObject();
		questionSet.put("questionArray", quizObject);
		this.saveDialogue(questionSet.toJSONString() , finishHandler);
	}
	
	/**
	 * Export the entire question bank.
	 * @param finishHandler Handler for what to do when done.
	 */
	protected void exportQuestionBank(EventHandler finishHandler) {
		JSONArray bankObject = new JSONArray();
		for(BasicQuestion question: QuestionBank.master.getAllQuestions()) {
			bankObject.add(question.getJSONObject());
		}
		JSONObject questionSet = new JSONObject();
		questionSet.put("questionArray", bankObject);
		this.saveDialogue(questionSet.toJSONString() , finishHandler);
	}
}
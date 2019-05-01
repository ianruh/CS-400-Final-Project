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
 * A utility class to import and export JSON objects.
 *
 */
public class ImportExportUtility {
	// The stage used to display the pop-ups
	protected Stage primaryStage;

	// The singleton instance with the stage
	protected static ImportExportUtility master;

	/**
	 * Constructor that should only be called once.
	 * 
	 * @param primaryStage
	 */
	protected ImportExportUtility(Stage primaryStage) {
		this.primaryStage = primaryStage;
		ImportExportUtility.master = this;
	}

	
	/**
	 * Utility method for opening and writing to a file.
	 * @param content Content to be written.
	 * @param file File to write to.
	 */
	private void saveFile(String content, File file) {
		// Attempt save
		try {
			FileWriter fileWriter = null;
			fileWriter = new FileWriter(file);
			fileWriter.write(content);
			fileWriter.close();
		} catch (IOException ex) {
			// Print exception if it occurs
			ex.printStackTrace();
		}

	}

	/**
	 * Method called to open a dialog to save a file.
	 * @param text Text of the file to save.
	 * @param handler Handler the finish.
	 */
	protected void saveDialogue(String text, EventHandler handler) {
		FileChooser fileChooser = new FileChooser();
		
		// Set extension filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
		fileChooser.getExtensionFilters().add(extFilter);

		// Show save file dialog
		File file = fileChooser.showSaveDialog(this.primaryStage);

		// Save if not null
		if (file != null) {
			saveFile(text, file);
			handler.handleEvent();
		}
	}

	/**
	 * Shows a dialogue to choose a file.
	 * 
	 * @return the file object, or null if non selected.
	 */
	protected File selectJSONFile() {
		// Create file chooser to browse for json file
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("JSON Files", "*.json"));
		return fileChooser.showOpenDialog(primaryStage);
	}

	/**
	 * Shows a dialogue to choose an image file.
	 * 
	 * @return the string for path, none if invalid
	 */
	protected String selectIMGFile() {
		
		// Create a file chooser to browse for image file
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Resource File");
		fileChooser.getExtensionFilters().add(new ExtensionFilter("PNG Files", "*.png"));
		File imageFile = fileChooser.showOpenDialog(primaryStage);

		// Format the path to javaFX-Image friendly format
		String returnPath;
		try {
			returnPath = imageFile.toURI().toURL().toExternalForm();
		} catch (Exception e) {
			returnPath = "none";
		}

		// Return path
		return returnPath;
	}

	/**
	 * This method takes a file object, and attempts to parse it as a JSON file
	 * containing questions of the correct format.
	 * 
	 * @param file
	 *            The file to be parsed.
	 * @return a list of the questions.
	 */
	protected List<BasicQuestion> importQuestions(File file) {
		ArrayList<BasicQuestion> questions = new ArrayList<BasicQuestion>();
		try {
			// Parsing file "JSONExample.json"
			Object object = new JSONParser().parse(new FileReader(file));

			// Typecasting obj to JSONObject
			JSONObject jsonObject = (JSONObject) object;

			// Getting phoneNumbers
			JSONArray questionJSONArray = (JSONArray) jsonObject.get("questionArray");

			// Loop through JSON question array
			for (Object questionObject : questionJSONArray) {
				
				// Read question data
				JSONObject question = (JSONObject) questionObject;
				String questionText = (String) question.get("questionText");
				String topic = (String) question.get("topic");
				String image = (String) question.get("image");
				ArrayList<String> answers = new ArrayList<String>();
				JSONArray answerArray = (JSONArray) question.get("choiceArray");
				
				// Loop through answer choices
				int index = 0;
				int correctIndex = -1;
				for (Object choiceObject : answerArray) {
					JSONObject choice = (JSONObject) choiceObject;
					String answerText = (String) choice.get("choice");
					answers.add(answerText);
					if (((String) choice.get("isCorrect")).equals("T")) {
						correctIndex = index;
					}
					index++;
				}
				
				// No correct answers case
				if (correctIndex == -1) {
					throw new RuntimeException("No answers were correct.");
				}
				
				// Handle no-image case
				if (image.equals("none")) {
					image = null;
				}
				
				// Create & add the new question
				BasicQuestion newQuestion = new BasicQuestion(questionText, answers, correctIndex, topic, image);
				questions.add(newQuestion);
			}

		} catch (Exception e) {
			// Catch exception
			System.out.println("Exception thrown while attempting to parse json file.");
			e.printStackTrace();
		}
		return questions;
	}

	/**
	 * Exporter for a quiz.
	 * 
	 * @param quiz
	 *            Quiz to export.
	 * @param finishHandler
	 *            Event handler for what to do when done.
	 */
	protected void exportQuiz(BasicQuiz quiz, EventHandler finishHandler) {
		// Create JSON Array
		JSONArray quizObject = new JSONArray();
		for (BasicQuestion question : quiz.questions) {
			quizObject.add(question.getJSONObject());
		}
		JSONObject questionSet = new JSONObject();
		questionSet.put("questionArray", quizObject);
		
		// Display save dialogue
		this.saveDialogue(questionSet.toJSONString(), finishHandler);
	}

	/**
	 * Export the entire question bank.
	 * 
	 * @param finishHandler
	 *            Handler for what to do when done.
	 */
	protected void exportQuestionBank(EventHandler finishHandler) {
		// Create JSON Array for question bank
		JSONArray bankObject = new JSONArray();
		for (BasicQuestion question : QuestionBank.master.getAllQuestions()) {
			bankObject.add(question.getJSONObject());
		}
		JSONObject questionSet = new JSONObject();
		questionSet.put("questionArray", bankObject);
		
		// Display save dialogue
		this.saveDialogue(questionSet.toJSONString(), finishHandler);
	}
}
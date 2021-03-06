/**
 * Final Project. Quiz Generator.
 *
 * Filename:   QuestionBank
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class that stores all of the questions in the quiz generator.
 *
 */
public class QuestionBank {
	
	// Singleton instance
	public static QuestionBank master;
	
	// Map used to store questions.
	private HashMap<String, ArrayList<BasicQuestion>> table;
	
	/**
	 * Basic constructor. Should only ever be called once.
	 */
	public QuestionBank() {
		QuestionBank.master = this;
		this.table = new HashMap<String, ArrayList<BasicQuestion>>();
	}
	
	/**
	 * Adds a question to the bank.
	 * @param newQuestion Question to add
	 */
	protected void addQuestion(BasicQuestion newQuestion) {
		if(!this.getTopics().contains(newQuestion.topic)) {
			this.table.put(newQuestion.topic, new ArrayList<BasicQuestion>());
		}
		this.table.get(newQuestion.topic).add(newQuestion);
	}
	
	/**
	 * Adds a list of question to the bank.
	 * @param questions Question to be added.
	 */
	protected void addQuestions(List<BasicQuestion> questions) {
		for(BasicQuestion question: questions) {
			this.addQuestion(question);
		}
	}
	
	/**
	 * Generates a new quiz with the specified number of questions
	 * from the specified topics. ALl returned questions are unique.
	 * @param numQuestion
	 * @param topics
	 * @return The list of questions in the quiz.
	 */
	protected List<BasicQuestion> getNewQuiz(int numQuestion, List<String> topics) {
		ArrayList<BasicQuestion> fullSet = new ArrayList<BasicQuestion>();
		for(String topic: topics) {
			if(this.getTopics().contains(topic))
				fullSet.addAll(this.table.get(topic));
		}
		
		List<BasicQuestion> distinctSet = fullSet.stream().distinct().collect(Collectors.toList());
		Collections.shuffle(distinctSet);
		
		return distinctSet.parallelStream().limit(numQuestion).collect(Collectors.toList());
	}
	
	/**
	 * Get the keys that are in the question bank.
	 * @return A list of the keys.
	 */
	protected List<String> getTopics() {
		List<String> list = this.convertSetToList(this.table.keySet());
		Collections.sort(list);
		return list;
	}
	
	/**
	 * Utility method to convert sets to lists.
	 * @param set Set to convert
	 * @return The converted set as a list.
	 */
	private List<String> convertSetToList(Set<String> set) {
		ArrayList<String> keys = new ArrayList<String>();
		for(String key: set) {
			keys.add(key);
		}
		return keys;
	}
	
	/**
	 * Method to get the number of questions in the question bank.
	 * @return the number of questions in the question bank.
	 */
	protected int getNumQuestions() {
		int sum = 0;
		for(ArrayList<BasicQuestion> list: this.table.values()) {
			sum += list.size();
		}
		return sum;
	}
	
	/**
	 * Method to get all of the questions in the question bank.
	 * @return A list of all of the questions.
	 */
	protected List<BasicQuestion> getAllQuestions() {
		ArrayList<BasicQuestion> allQuestions = new ArrayList<BasicQuestion>();
		for(List<BasicQuestion> list: this.table.values()) {
			allQuestions.addAll(list);
		}
		return allQuestions;
	}
	
	/**
	 * Get some sample questions. Is not used in production, but can be called from
	 * the constructor to add samples.
	 * @return
	 */
	protected static List<BasicQuestion> getQuestions() {
		///////////////////////// Sample Data ///////////////////////////
				
		// Question 1
		ArrayList<String> answers1 = new ArrayList<String>();
		answers1.add("Correct Answer");
		answers1.add("Answer 2");
		answers1.add("Answer 3");
		answers1.add("Answer 4");
		BasicQuestion question1 = new BasicQuestion("Example question?", answers1, 0, "Topic One", "none");
		
		// Question 2
		ArrayList<String> answers2 = new ArrayList<String>();
		answers2.add("Inorrect Answer");
		answers2.add("Answer 2");
		answers2.add("Answer 3");
		answers2.add("Answer 4 <-- This one");
		BasicQuestion question2 = new BasicQuestion("Yep, this is an example as well, but with more text.", answers2, 3, "Topic Two", "none");
		
		// Question 3
		ArrayList<String> answers3 = new ArrayList<String>();
		answers3.add("True");
		answers3.add("False");
		answers3.add("Tralse");
		BasicQuestion question3 = new BasicQuestion("And this is a really really long question Lorem ipsum dolor sit amet, "
			+ "consectetur adipiscing elit. Nunc elit nisi, interdum non volutpat a, scelerisque et neque. Mauris molestie "
			+ "nec orci at hendrerit. Nunc sit amet quam purus. Donec elementum metus vel erat sollicitudin pharetra. "
			+ "Curabitur neque nisi, consequat quis lorem non, efficitur faucibus velit. Ut in accumsan nulla. Maecenas "
			+ "consequat ipsum eu urna consectetur, nec bibendum augue cursus. Ut viverra fringilla luctus. Vivamus nec "
			+ "tincidunt est. Curabitur volutpat maximus felis ut vestibulum. HINT: It's true", answers3, 0, "Topic Three", "none");
		
		// Create the list
		ArrayList<BasicQuestion> questions = new ArrayList<BasicQuestion>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		return questions;
	}
}

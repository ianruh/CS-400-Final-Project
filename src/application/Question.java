/**
 * Final Project. Quiz Generator.
 *
 * Filename:   Question
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

import java.util.List;

/**
 * Interface that defines how class will interact with a question.
 * @author ianruh
 *
 */
public interface Question {
	
	/**
	 * Get an ArrayList containing all of the possible answers for the program.
	 * @return all of the answers.
	 */
	public List<String> getAnswers();
}

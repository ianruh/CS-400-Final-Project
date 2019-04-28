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

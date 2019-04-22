package application;

import java.util.ArrayList;

public interface Question {
	
	/**
	 * Get an ArrayList containing all of the possible answers for the program.
	 * @return all of the answers.
	 */
	public ArrayList<String> getAnswers();
}

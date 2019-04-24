package application;

import java.util.ArrayList;

/**
 * Class that stores all of the questions in the quiz generator.
 * @author ianruh
 *
 */
public class QuestionBank {
	
	private static String cats = "https://cataas.com/cat?width=200";
	
	/**
	 * Get some sample questions.
	 * @return
	 */
	public static ArrayList<BasicQuestion> getQuestions() {
		///////////////////////// Sample Data ///////////////////////////
				
		// Question 1
		ArrayList<String> answers1 = new ArrayList<String>();
		answers1.add("Correct Answer");
		answers1.add("Answer 2");
		answers1.add("Answer 3");
		answers1.add("Answer 4");
		BasicQuestion question1 = new BasicQuestion("Example question?", answers1, 0, "topic string", cats);
		
		// Question 2
		ArrayList<String> answers2 = new ArrayList<String>();
		answers2.add("Inorrect Answer");
		answers2.add("Answer 2");
		answers2.add("Answer 3");
		answers2.add("Answer 4 <-- This one");
		BasicQuestion question2 = new BasicQuestion("Yep, this is an example as well, but with more text.", answers2, 3, "topic string", cats);
		
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
			+ "tincidunt est. Curabitur volutpat maximus felis ut vestibulum. HINT: It's true", answers3, 0, "topic string", cats);
		
		ArrayList<BasicQuestion> questions = new ArrayList<BasicQuestion>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		return questions;
	}
}

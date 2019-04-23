package application;

/**
 * Interface for how the quiz will communicate with other objects.
 * @author ianruh
 *
 */
public interface Quiz {
	/**
	 * Get the percentage of correctly answered questions.
	 * @return the percentage of correctly answered questions
	 */
	public double getPercentage();
}

package ComputerScience.Labs.Quiz;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class Question {
	protected String text;
	protected String answer;

	/**
	 Constructs a question with empty question and answer.
	 */
	public Question(){
		text = "";
		answer = "";
	}

	/**
	 Sets the question text.
	 @param questionText the text of this question
	 */
	public void setText(String questionText){
		text = questionText;
	}

	/**
	 Sets the answer for this question.
	 @param correctResponse the answer
	 */
	public void setAnswer(String correctResponse){
		answer = correctResponse;
	}

	/**
	 Checks a given response for correctness.
	 @param response the response to check
	 @return true if the response was correct, false otherwise
	 */
	public boolean checkAnswer(String response){
		return response.equals(answer);
	}

	/**
	 Displays this question.
	 */
	public void display(){
		System.out.print(text);
	}
}
package utils;

public class InputUtils {

	public static boolean isAnswerHit(String answer) {
		if (answer.equals("y"))
			return true;
		if (answer.equals("n"))
			return false;
		throw new IllegalArgumentException("입력은 y/n로만 해주세요.");
	}
}

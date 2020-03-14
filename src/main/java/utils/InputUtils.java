package utils;

public class InputUtils {
	private static final String ERROR_MESSAGE_IS_HIT_ANSWER = "y,n 중 하나만 입력하세요.";
	private static final String ANSWER_Y = "y";
	private static final String ANSWER_N = "n";

	public static boolean isHitToBoolean(String answer) {
		if (answer.equalsIgnoreCase(ANSWER_Y)) {
			return true;
		}
		if (answer.equalsIgnoreCase(ANSWER_N)) {
			return false;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_IS_HIT_ANSWER);
	}
}

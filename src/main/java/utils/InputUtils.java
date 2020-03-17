package utils;

public class InputUtils {
	private static final String ERROR_MESSAGE_IS_HIT_ANSWER = "y,n 중 하나만 입력하세요.";
	private static final String ANSWER_Y = "y";
	private static final String ANSWER_N = "n";

	public static boolean isHitToBoolean(String answer) {
		if (ANSWER_Y.equalsIgnoreCase(answer)) {
			return true;
		}
		if (ANSWER_N.equalsIgnoreCase(answer)) {
			return false;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_IS_HIT_ANSWER);
	}


}

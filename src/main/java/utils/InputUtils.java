package utils;

public class InputUtils {
	private static final String ERROR_MESSAGE_IS_HIT_ANSWER = "y,n 중 하나만 입력하세요.";
	private static final String ANSWER_Y_CAPITAL = "Y";
	private static final String ANSWER_N_CAPITAL = "N";
	private static final String ANSWER_Y = "y";
	private static final String ANSWER_N = "n";

	public static boolean isHitToBoolean(String answer) {
		if (answer.equals(ANSWER_Y_CAPITAL) || answer.equals(ANSWER_Y)) {
			return true;
		}
		if (answer.equals(ANSWER_N_CAPITAL) || answer.equals(ANSWER_N)) {
			return false;
		}
		throw new IllegalArgumentException(ERROR_MESSAGE_IS_HIT_ANSWER);
	}
}

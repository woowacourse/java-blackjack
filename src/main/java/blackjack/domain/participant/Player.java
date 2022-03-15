package blackjack.domain.participant;

public class Player extends Participant {
	private static final String BLANK = "";
	private static final String ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
	private static final String ALLOWED_CHARACTERS = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣_]+.*";
	private static final String ERROR_MESSAGE_UNAVAILABLE_CHARACTER = "[ERROR] 이름에 특수문자가 포함될 수 없습니다.";

	private Player(String name) {
		super(name);
	}

	public static Player from(String input) {
		return new Player(validateName(input.trim()));
	}

	private static String validateName(String input) {
		checkBlankName(input);
		checkUnavailableName(input);
		return input;
	}

	private static void checkBlankName(String input) {
		if (input.equals(BLANK)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_NAME);
		}
	}

	private static void checkUnavailableName(String input) {
		if (input.matches(ALLOWED_CHARACTERS)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNAVAILABLE_CHARACTER);
		}
	}

	@Override
	public boolean shouldHit() {
		return false;
	}
}

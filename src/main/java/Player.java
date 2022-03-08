public class Player {
	private static final String ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
	private static final String ERROR_MESSAGE_UNAVAILABLE_CHARACTER = "[ERROR] 이름에 특수문자가 포함될 수 없습니다.";
	private static final String ALLOWED_CHARACTERS = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣_]+.*";

	private final String name;

	public Player(String input) {
		validateName(input);
		this.name = input;
	}

	private void validateName(String input) {
		checkBlankName(input);
		checkUnavailableName(input);
	}

	private void checkBlankName(String input) {
		if (input.equals("")) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_NAME);
		}
	}

	private void checkUnavailableName(String input) {
		if (input.matches(ALLOWED_CHARACTERS)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNAVAILABLE_CHARACTER);
		}
	}

}

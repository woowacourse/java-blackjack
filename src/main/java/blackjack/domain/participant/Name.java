package blackjack.domain.participant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Name {
	private static final String BLANK = "";
	private static final String ERROR_MESSAGE_EMPTY_NAME = "[ERROR] 이름은 공백일 수 없습니다.";
	private static final Pattern ALLOWED_CHARACTERS = Pattern.compile(".*[^0-9a-zA-Zㄱ-ㅎ가-힣_]+.*");
	private static final String ERROR_MESSAGE_UNAVAILABLE_CHARACTER = "[ERROR] 이름에 특수문자가 포함될 수 없습니다.";

	private final String name;

	public Name(String name) {
		this.name = validateName(name);
	}

	private static String validateName(String input) {
		String name = input.trim();
		checkBlankName(name);
		checkUnavailableName(name);
		return name;
	}

	private static void checkBlankName(String input) {
		if (input.equals(BLANK)) {
			throw new IllegalArgumentException(ERROR_MESSAGE_EMPTY_NAME);
		}
	}

	private static void checkUnavailableName(String input) {
		Matcher matcher = ALLOWED_CHARACTERS.matcher(input);
		if (matcher.matches()) {
			throw new IllegalArgumentException(ERROR_MESSAGE_UNAVAILABLE_CHARACTER);
		}
	}

	public String getName() {
		return name;
	}
}

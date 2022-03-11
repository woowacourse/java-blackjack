package blackjack.domain;

import java.util.regex.Pattern;

public class Name {
	private static final String BLANK_NAME = "[ERROR] 이름이 빈 입력이면 안됩니다.";
	private static final String SPACE_IN_NAME = "[ERROR] 이름에 공백이 있으면 안됩니다.";
	private static final Pattern BLANK = Pattern.compile("[\\s]+");
	private final String name;

	public Name(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException(BLANK_NAME);
		}
		if (BLANK.matcher(name).matches()) {
			throw new IllegalArgumentException(SPACE_IN_NAME);
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

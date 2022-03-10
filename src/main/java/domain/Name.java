package domain;

import java.util.regex.Pattern;

public class Name {
	private final String name;
	private static final Pattern BLANK = Pattern.compile("[\\s]+");

	public Name(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("이름이 빈 입력이면 안됩니다.");
		}
		if (BLANK.matcher(name).matches()) {
			throw new IllegalArgumentException("이름에 공백이 있으면 안됩니다.");
		}
		this.name = name;
	}
}

package domain.user;

import java.util.Objects;

public class Name {
	public static final String INPUT_EMPTY_NAME = "이름이 빈 문자열입니다.";
	public static final String INPUT_NULL_NAME = "이름이 null일 수 없습니다.";

	private final String name;

	public Name(String name) {
		Objects.requireNonNull(name, INPUT_NULL_NAME);
		if (name.isEmpty()) {
			throw new IllegalArgumentException(INPUT_EMPTY_NAME);
		}
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

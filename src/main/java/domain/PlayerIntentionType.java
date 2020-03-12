package domain;

import java.util.Arrays;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public enum PlayerIntentionType {
	YES("y"),
	NO("n");

	private final String value;

	PlayerIntentionType(String value) {
		this.value = value;
	}

	public static PlayerIntentionType of(String input) {
		return Arrays.stream(PlayerIntentionType.values())
				.filter(value -> value.getValue().equals(input))
				.findFirst()
				.orElseThrow(NullPointerException::new);
	}

	public String getValue() {
		return value;
	}
}

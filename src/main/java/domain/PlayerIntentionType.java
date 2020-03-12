package domain;

import java.util.Arrays;

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

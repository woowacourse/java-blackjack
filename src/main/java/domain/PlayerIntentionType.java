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
			.filter(type -> type.value.equals(input))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}
}

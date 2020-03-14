package util;

import java.util.Arrays;

public enum PlayerDrawResponse {
	YES("y"),
	NO("n");

	private static final String ILLEGAL_VALUE_MESSAGE = "올바른 선택 값이 아닙니다.";

	private final String value;

	PlayerDrawResponse(String value) {
		this.value = value;
	}

	public static boolean isYes(String value) {
		return of(value) == YES;
	}

	private static PlayerDrawResponse of(String value) {
		return Arrays.stream(values())
			.filter(playerDrawResponse -> playerDrawResponse.value.equalsIgnoreCase(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_VALUE_MESSAGE));
	}
}

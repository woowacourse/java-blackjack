package util;

import java.util.Arrays;

public enum DrawResponse {
	YES("y"),
	NO("n");

	private static final String ILLEGAL_VALUE_MESSAGE = "올바른 선택 값이 아닙니다.";

	private final String value;

	DrawResponse(String value) {
		this.value = value;
	}

	public static boolean isYes(String value) {
		return of(value) == YES;
	}

	private static DrawResponse of(String value) {
		return Arrays.stream(values())
			.filter(drawResponse -> drawResponse.value.equalsIgnoreCase(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_VALUE_MESSAGE));
	}
}

package util;

import java.util.Arrays;

public enum YesOrNo {
	YES("y"),
	NO("n");

	private static final String ILLEGAL_VALUE_MESSAGE = "올바른 선택 값이 아닙니다.";

	private final String value;

	YesOrNo(String value) {
		this.value = value;
	}

	public static YesOrNo of(String value) {
		return Arrays.stream(values())
			.filter(yesOrNo -> yesOrNo.value.equalsIgnoreCase(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_VALUE_MESSAGE));
	}

	public boolean isYes() {
		return this == YES;
	}
}

package domain;

import java.util.Arrays;

public enum MoreDrawResponse {
	YES("y"),
	NO("n");

	private static final String ILLEGAL_DRAW_RESPONSE_VALUE_MESSAGE = "올바른 선택 값이 아닙니다.";

	private final String response;

	MoreDrawResponse(String response) {
		this.response = response;
	}

	public static MoreDrawResponse of(String response) {
		return Arrays.stream(values())
			.filter(drawResponse -> drawResponse.response.equalsIgnoreCase(response))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(ILLEGAL_DRAW_RESPONSE_VALUE_MESSAGE));
	}

	public boolean isMoreDraw() {
		return this == YES;
	}
}

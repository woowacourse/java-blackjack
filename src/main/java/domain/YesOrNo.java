package domain;

import java.util.Arrays;

/**
 *    YesOrNo를 의미하는 enum입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public enum YesOrNo {
	YES("y"),
	NO("n");

	private String decision;

	YesOrNo(String input) {
		this.decision = input;
	}

	public static YesOrNo of(String input) {
		return Arrays.stream(YesOrNo.values())
			.filter(value -> value.decision.equalsIgnoreCase(input))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("y또는 n을 입력해주세요."));
	}

	public boolean isYes() {
		return this == YES;
	}
}

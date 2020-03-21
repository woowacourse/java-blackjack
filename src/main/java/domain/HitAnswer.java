package domain;

import java.util.Arrays;

/**
 *    Hit answer를 의미하는 enum입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public enum HitAnswer {
	YES("y"),
	NO("n");

	private String decision;

	HitAnswer(String input) {
		this.decision = input;
	}

	public static HitAnswer of(String input) {
		return Arrays.stream(HitAnswer.values())
			.filter(value -> value.decision.equalsIgnoreCase(input))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("y또는 n을 입력해주세요."));
	}

	public boolean isYes() {
		return this == YES;
	}
}

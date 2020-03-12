package domain.gamer;

import java.util.Arrays;

public enum YesOrNo {
	YES("Y"),
	NO("N");

	private final String state;

	YesOrNo(String state) {
		this.state = state;
	}

	public static YesOrNo of(String input) {
		if (!input.toUpperCase().equals("Y") && !input.toUpperCase().equals("N")) {
			throw new IllegalArgumentException("Y 또는 N을 입력해야합니다.");
		}
		return Arrays.stream(YesOrNo.values())
			.filter(yesOrNo -> yesOrNo.state.equals(input.toUpperCase()))
			.findFirst()
			.get();
	}

	public boolean isYes() {
		return this == YES;
	}
}
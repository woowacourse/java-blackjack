package domain.gamer;

import java.util.Arrays;

public enum Answer {
	YES("Y"),
	NO("N");

	private final String state;

	Answer(String state) {
		this.state = state;
	}

	public static Answer from(String input) {
		return Arrays.stream(Answer.values())
				.filter(answer -> answer.state.equals(input.toUpperCase()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 대답입니다."));
	}

	public boolean isYes() {
		return this == YES;
	}
}
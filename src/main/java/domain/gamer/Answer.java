package domain.gamer;

import java.util.Arrays;

public enum Answer {
	YES("Y"),
	NO("N");

	private final String state;

	Answer(String state) {
		this.state = state;
	}

	public static Answer of(String input) {
		return Arrays.stream(Answer.values())
			.filter(answer -> answer.state.equals(input.toUpperCase()))
			.findFirst()
			.orElseThrow(()-> new IllegalArgumentException(String.format("%s는 올바르지 않은 타입입니다.",input)));
	}

	public boolean isYes() {
		return this == YES;
	}
}
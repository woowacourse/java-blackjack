package blackjack.domain;

import java.util.Arrays;

public enum Answer {

	YES("y"),
	NO("n");

	private final String answer;

	Answer(final String answer) {
		this.answer = answer;
	}

	public static Answer of(final String input) {
		return Arrays.stream(values())
			.filter(value -> value.getAnswer().equals(input))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 응답입니다."));
	}

	public String getAnswer() {
		return answer;
	}
}

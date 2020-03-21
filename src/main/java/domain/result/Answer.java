package domain.result;

import java.util.Arrays;

import exception.AnswerFormatException;

public enum Answer {
	YES("y"),
	NO("n");

	private String answerValue;

	Answer(String answerValue) {
		this.answerValue = answerValue;
	}

	public boolean isYes() {
		return this == YES;
	}

	public static Answer findAnswer(String answer) {
		return Arrays.stream(values())
			.filter(x -> x.answerValue.equals(answer.toLowerCase()))
			.findFirst()
			.orElseThrow(AnswerFormatException::new);
	}
}

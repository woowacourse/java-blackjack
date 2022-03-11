package blackjack.domain;

import java.util.Arrays;

public enum RedrawChoice {
	YES("y"),
	NO("n"),
	;

	private final String value;

	RedrawChoice(final String value) {
		this.value = value;
	}

	public static RedrawChoice of(String choice) {
		return Arrays.stream(values())
			.filter(redrawChoice -> choice.equals(redrawChoice.getValue()))
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
	}

	public String getValue() {
		return value;
	}
}

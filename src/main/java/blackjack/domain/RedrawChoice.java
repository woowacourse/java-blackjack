package blackjack.domain;

import java.util.Arrays;
import java.util.Locale;

public enum RedrawChoice {
	YES("y"),
	NO("n"),
	;

	private final String value;

	RedrawChoice(final String value) {
		this.value = value;
	}

	public static RedrawChoice of(String choice) {
		final String lowercaseChoice = choice.toLowerCase(Locale.ROOT);
		return Arrays.stream(values())
			.filter(redrawChoice -> lowercaseChoice.equals(redrawChoice.getValue()))
			.findAny()
			.orElseThrow(IllegalArgumentException::new);
	}

	public String getValue() {
		return value;
	}
}

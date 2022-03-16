package blackjack.domain.role;

import java.util.Arrays;

public enum PlayerDrawChoice {
	YES("y"),
	NO("n"),
	;

	private final String value;

	PlayerDrawChoice(final String value) {
		this.value = value;
	}

	public static PlayerDrawChoice of(String choice) {
		return Arrays.stream(values())
				.filter(playerDrawChoice -> choice.equals(playerDrawChoice.getValue()))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}

	public String getValue() {
		return value;
	}
}

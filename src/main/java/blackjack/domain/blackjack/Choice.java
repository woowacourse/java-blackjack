package blackjack.domain.blackjack;

import java.util.Arrays;

import blackjack.domain.exceptions.InvalidChoiceException;

public enum Choice {
	HIT("y", true),
	STAND("n", false);

	private final String inputChoice;
	private final boolean choice;

	Choice(String inputChoice, boolean choice) {
		this.inputChoice = inputChoice;
		this.choice = choice;
	}

	public static Choice of(String inputChoice) {
		return Arrays.stream(values())
			.filter(value -> value.inputChoice.equals(inputChoice))
			.findAny()
			.orElseThrow(() -> new InvalidChoiceException(InvalidChoiceException.INVALID));
	}

	public boolean isHit() {
		return choice;
	}
}

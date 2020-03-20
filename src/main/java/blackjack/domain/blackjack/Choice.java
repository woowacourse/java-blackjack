package blackjack.domain.blackjack;

import java.util.Arrays;
import java.util.Objects;

import blackjack.domain.exceptions.InvalidChoiceException;

public enum Choice {
	HIT("y"),
	STAND("n");

	private final String choice;

	Choice(String choice) {
		this.choice = choice;
	}

	public static Choice of(String inputChoice) {
		validate(inputChoice);
		return Arrays.stream(values())
			.filter(value -> value.choice.equals(inputChoice))
			.findAny()
			.orElseThrow(() -> new InvalidChoiceException(InvalidChoiceException.INVALID));
	}

	private static void validate(String inputChoice) {
		if (Objects.isNull(inputChoice) || inputChoice.isEmpty()) {
			throw new InvalidChoiceException(InvalidChoiceException.NULL);
		}
	}

	public boolean isHit() {
		return HIT.equals(this);
	}
}

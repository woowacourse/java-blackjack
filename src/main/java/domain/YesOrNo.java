package domain;

import java.util.Arrays;

import domain.exception.InvalidChoiceException;

/**
 *   Yes와 No를 가지는 enum
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public enum YesOrNo {
	YES("Y"),
	NO("N");

	private String choice;

	YesOrNo(String choice) {
		this.choice = choice;
	}

	public static YesOrNo getChoice(String choice) {
		return Arrays.stream(YesOrNo.values())
			.filter(c -> c.choice.equals(choice))
			.findFirst()
			.orElseThrow(() -> new InvalidChoiceException(choice));
	}

	public boolean isYes() {
		return this == YES;
	}
}

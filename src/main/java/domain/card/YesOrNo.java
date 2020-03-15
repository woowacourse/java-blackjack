package domain.card;

import java.util.Arrays;

/**
 *   class description
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
			.orElseThrow(() -> new IllegalArgumentException("Y또는 N중 하나를 입력해야합니다."));
	}

	public boolean isYes() {
		return this == YesOrNo.YES;
	}
}

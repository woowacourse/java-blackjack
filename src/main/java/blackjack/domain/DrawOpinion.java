package blackjack.domain;

import java.util.Arrays;

public enum DrawOpinion {
	YES("y"),
	NO("n");

	private final String opinion;

	DrawOpinion(String opinion) {
		this.opinion = opinion;
	}

	public static DrawOpinion of(String opinion) {
		return Arrays.stream(values())
			.filter(drawOpinion -> drawOpinion.opinion.equals(opinion))
			.findAny()
			.orElseThrow(() -> new InvalidDrawOpinionException(InvalidDrawOpinionException.INVALID));
	}
}

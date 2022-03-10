package blackjack.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Outcome {

	VICTORY("승", "DEFEAT", gapScore -> gapScore > 0),
	DEFEAT("패", "VICTORY", gapScore -> gapScore < 0),
	TIE("무", "TIE", gapScore -> gapScore == 0);

	private final String value;
	private final String oppositeValue;
	private final Predicate<Integer> competeStander;

	Outcome(final String value, final String oppositeValue, final Predicate<Integer> competeStander) {
		this.value = value;
		this.oppositeValue = oppositeValue;
		this.competeStander = competeStander;
	}

	public static Outcome of(int playerScore, int dealerScore) {
		final int gapScore = playerScore - dealerScore;
		return Arrays.stream(values())
			.filter(outcome -> outcome.matchThisOutcome(gapScore))
			.findFirst()
			.orElseThrow(NoSuchElementException::new);
	}

	private boolean matchThisOutcome(final int gapScore) {
		return competeStander.test(gapScore);
	}

	public String getValue() {
		return value;
	}

	public Outcome getOppositeOutcome() {
		return Outcome.valueOf(oppositeValue);
	}
}

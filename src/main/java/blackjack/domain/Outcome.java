package blackjack.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Outcome {

	VICTORY(gapScore -> gapScore > 0),
	DEFEAT(gapScore -> gapScore < 0),
	TIE(gapScore -> gapScore == 0);

	private final Predicate<Integer> competeStander;

	Outcome(final Predicate<Integer> competeStander) {
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

}

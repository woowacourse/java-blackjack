package blackjack.domain.game;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Outcome {

	BLACKJACK_VICTORY("DEFEAT", "1.5", gapScore -> gapScore > 0),
	VICTORY("DEFEAT", "1", gapScore -> gapScore > 0),
	DEFEAT("VICTORY", "-1", gapScore -> gapScore < 0),
	TIE("TIE", "0", gapScore -> gapScore == 0);

	private final String oppositeValue;
	private final String bettingMultiplier;
	private final Predicate<Integer> competeStander;

	Outcome(final String oppositeValue, final String bettingMultiplier, final Predicate<Integer> competeStander) {
		this.oppositeValue = oppositeValue;
		this.bettingMultiplier = bettingMultiplier;
		this.competeStander = competeStander;
	}

	public static Outcome ofBlackJack(boolean playerBlackjack, boolean dealerBlackJack) {
		if (playerBlackjack && !dealerBlackJack) {
			return BLACKJACK_VICTORY;
		}
		final int gapScore = Boolean.compare(playerBlackjack, dealerBlackJack);
		return createOutcome(gapScore);
	}

	public static Outcome of(int playerScore, int dealerScore) {
		final int gapScore = playerScore - dealerScore;
		return createOutcome(gapScore);
	}

	private static Outcome createOutcome(int gapScore) {
		return Arrays.stream(values())
				.filter(outcome -> outcome != BLACKJACK_VICTORY)
				.filter(outcome -> outcome.matchThisOutcome(gapScore))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	private boolean matchThisOutcome(final int gapScore) {
		return competeStander.test(gapScore);
	}

	public Outcome getOppositeOutcome() {
		return Outcome.valueOf(oppositeValue);
	}
}

package blackjack.domain.game;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public enum Outcome {

	BLACKJACK_VICTORY("1.5", gapScore -> gapScore > 0),
	VICTORY("1", gapScore -> gapScore > 0),
	DEFEAT("-1", gapScore -> gapScore < 0),
	TIE("0", gapScore -> gapScore == 0);

	private final String bettingMultiplier;
	private final Predicate<Integer> competeStander;

	Outcome(final String bettingMultiplier, final Predicate<Integer> competeStander) {
		this.bettingMultiplier = bettingMultiplier;
		this.competeStander = competeStander;
	}

	public static Outcome ofBlackJack(final boolean playerBlackjack, final boolean dealerBlackJack) {
		if (playerBlackjack && !dealerBlackJack) {
			return BLACKJACK_VICTORY;
		}
		final int gapScore = Boolean.compare(playerBlackjack, dealerBlackJack);
		return createOutcome(gapScore);
	}

	public static Outcome of(final int playerScore, final int dealerScore) {
		final int gapScore = playerScore - dealerScore;
		return createOutcome(gapScore);
	}

	private static Outcome createOutcome(final int gapScore) {
		return Arrays.stream(values())
				.filter(outcome -> outcome != BLACKJACK_VICTORY)
				.filter(outcome -> outcome.matchThisOutcome(gapScore))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}

	private boolean matchThisOutcome(final int gapScore) {
		return competeStander.test(gapScore);
	}

	public Money applyBettingMultiplier(final Money bettingMoney) {
		return bettingMoney.multiply(new BigDecimal(bettingMultiplier));
	}
}

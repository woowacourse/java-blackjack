package blakcjack.domain.outcome;

import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Outcome {
	BLACKJACK_WIN(1.5f,
			(dealer, player) -> player.isBlackJack() && !dealer.isBlackJack()),
	WIN(1f,
			(dealer, player) -> (!player.isBust() && dealer.isBust()) || (!player.isBust() && player.getScore() > dealer.getScore())),
	DRAW(0f,
			(dealer, player) -> !player.isBust() && player.getScore() == dealer.getScore()),
	LOSE(-1f,
			(dealer, player) -> true);

	private final float earningRate;
	private final BiFunction<Dealer, Player, Boolean> expression;

	Outcome(final float earningRate, final BiFunction<Dealer, Player, Boolean> expression) {
		this.earningRate = earningRate;
		this.expression = expression;
	}

	public static Outcome of(final Dealer dealer, final Player player) {
		return Arrays.stream(values())
				.filter(outcome -> outcome.expression.apply(dealer, player))
				.findFirst()
				.orElseThrow(NoSuchOutcomeException::new);
	}

	public float getEarningRate() {
		return earningRate;
	}
}

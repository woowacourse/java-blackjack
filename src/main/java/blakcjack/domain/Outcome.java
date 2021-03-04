package blakcjack.domain;

import blakcjack.domain.participant.Dealer;
import blakcjack.domain.participant.Player;

public enum Outcome {
	WIN, DRAW, LOSE;

	public static Outcome of(final Player player, final Dealer dealer) {
		if (player.isBust()) {
			return LOSE;
		} else if (dealer.isBust()) {
			return WIN;
		}
		return calculate(player.calculateScore(), dealer.calculateScore());
	}

	private static Outcome calculate(final int playerScore, final int dealerScore) {
		if (playerScore > dealerScore) {
			return WIN;
		}
		if (playerScore < dealerScore) {
			return LOSE;
		}
		return DRAW;
	}
}

package money;

import duel.DuelResult;
import paticipant.Player;

public class WagerResultCalculator {
	private static final double WAGER_LOSE = 0;
	private static final double WAGER_WIN = 2;
	private static final double BLACKJACK_WIN_WAGER = 2.5;

	public Money calculate(final Player player, final Money wager) {
		if (player.isBust() && player.calculateDuelResult() == DuelResult.LOSE) {
			return wager.multiply(WAGER_LOSE);
		}
		if (player.calculateDuelResult() == DuelResult.DRAW) {
			return wager;
		}
		if (player.calculateDuelResult() == DuelResult.WIN && player.isBlackjack()) {
			return wager.multiply(BLACKJACK_WIN_WAGER);
		}
		if (player.calculateDuelResult() == DuelResult.WIN) {
			return wager.multiply(WAGER_WIN);
		}
		return null;
	}
}

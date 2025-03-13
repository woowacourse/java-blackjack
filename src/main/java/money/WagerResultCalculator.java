package money;

import duel.DuelResult;
import paticipant.Player;

public class WagerResultCalculator {
	private static final double WAGER_LOSE = 0;

	public Money calculate(final Player player, final Money wager) {
		if (player.isBust() && player.calculateDuelResult() == DuelResult.LOSE) {
			return wager.multiply(WAGER_LOSE);
		}
		if (player.calculateDuelResult() == DuelResult.DRAW) {
			return wager;
		}
		return null;
	}
}

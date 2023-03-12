package domain.result;

import domain.Dealer;
import domain.Player;

public class PlayerResult implements Result {

	private final String name;
	private final int profit;

	private PlayerResult(final String name, final int profit) {
		this.name = name;
		this.profit = profit;
	}

	public static PlayerResult decide(final Player player, final Dealer dealer) {
		if (isBlackJackVictory(player, dealer)) {
			return new PlayerResult(player.getName(), (int)(ResultState.BLACKJACK.getMultiplier() * player.getBet()));
		}
		if (isVictory(player, dealer)) {
			return new PlayerResult(player.getName(), (int)(ResultState.WIN.getMultiplier() * player.getBet()));
		}
		if (isDraw(player, dealer)) {
			return new PlayerResult(player.getName(), (int)(ResultState.DRAW.getMultiplier() * player.getBet()));
		}
		return new PlayerResult(player.getName(), (int)(ResultState.DEFEAT.getMultiplier() * player.getBet()));
	}

	private static boolean isBlackJackVictory(final Player player, final Dealer dealer) {
		return player.isBlackJack() && !dealer.isBlackJack();
	}

	private static boolean isVictory(final Player player, final Dealer dealer) {
		return (!player.isBust() && !dealer.isBust() && player.getScore() > dealer.getScore())
			|| (!player.isBust() && dealer.isBust());
	}

	private static boolean isDraw(final Player player, final Dealer dealer) {
		return (player.isBust() && dealer.isBust())
			|| (!player.isBlackJack() && !dealer.isBlackJack() && (player.getScore() == dealer.getScore()))
			|| (player.isBlackJack() && dealer.isBlackJack());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getProfit() {
		return profit;
	}
}

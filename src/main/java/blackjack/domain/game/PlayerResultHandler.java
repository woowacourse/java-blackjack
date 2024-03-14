package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public class PlayerResultHandler {

	private final Dealer dealer;

	public PlayerResultHandler(Dealer dealer) {
		this.dealer = dealer;
	}

	public GameResult getPlayerResult(Player player) {
		if (player.isBusted()) {
			return GameResult.LOSE;
		}
		if (dealer.isBusted()) {
			return getResultWhenDealerOnlyBusted(player);
		}
		return getResultWhenAllNotBusted(player);
	}

	private GameResult getResultWhenDealerOnlyBusted(Player player) {
		if (player.isBlackjack()) {
			return GameResult.BLACKJACK_WIN;
		}
		return GameResult.WIN;
	}

	private GameResult getResultWhenAllNotBusted(Player player) {
		if (dealer.isBlackjack()) {
			return getResultWhenDealerBlackjack(player);
		}
		if (player.isBlackjack()) {
			return GameResult.BLACKJACK_WIN;
		}
		return getResultByComparingScore(player);
	}

	private GameResult getResultWhenDealerBlackjack(Player player) {
		if (player.isBlackjack()) {
			return GameResult.DRAW;
		}
		return GameResult.LOSE;
	}

	private GameResult getResultByComparingScore(Player player) {
		int dealerScore = dealer.getScore();
		int playerScore = player.getScore();

		if (dealerScore > playerScore) {
			return GameResult.LOSE;
		}
		if (dealerScore == playerScore) {
			return GameResult.DRAW;
		}
		return GameResult.WIN;
	}
}

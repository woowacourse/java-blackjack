package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class ResultCalculator {
	private final Dealer dealer;
	private final Player player;

	public ResultCalculator(Dealer dealer, Player player) {
		this.dealer = dealer;
		this.player = player;
	}

	Boolean isBlackjack() {
		return !(dealer.isBlackjack()) && player.isBlackjack();
	}

	Boolean isLose() {
		return dealer.isBlackjack() && !(player.isBlackjack())
			|| player.isBust() || dealer.getScore() > player.getScore();
	}

	Boolean isWin() {
		return dealer.isBust() && !(player.isBust()) || (dealer.getScore() < player.getScore());
	}

	Boolean isDraw() {
		return dealer.isBlackjack() && player.isBlackjack() || dealer.getScore() == player.getScore();
	}
}

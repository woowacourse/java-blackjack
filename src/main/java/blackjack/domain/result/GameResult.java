package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public enum GameResult {
	WIN,
	LOSE;

	public static GameResult of(Dealer dealer, Player player) {
		if (player.isBust() || player.getScore() <= dealer.getScore()) {
			return LOSE;
		}
		return WIN;
	}

	public GameResult reverse() {
		if (this == WIN) {
			return LOSE;
		}
		return WIN;
	}
}

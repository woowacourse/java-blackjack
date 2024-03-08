package blackjack.domain;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;

public enum GameResult {
	WIN,
	LOSE;

	// TODO: 조건 재정의
	public static GameResult of(Dealer dealer, Player player) {
		if (player.isBust() || player.getScore() <= dealer.getScore()) {
			return GameResult.LOSE;
		}
		return GameResult.WIN;
	}

	public GameResult reverse() {
		if (this.equals(GameResult.WIN)) {
			return GameResult.LOSE;
		}
		return GameResult.WIN;
	}
}

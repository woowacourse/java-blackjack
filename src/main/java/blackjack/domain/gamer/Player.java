package blackjack.domain.gamer;

import blackjack.domain.BlackjackConstants;

public class Player extends BlackjackGamer {

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= BlackjackConstants.BLACKJACK_VALUE.getValue();
	}

	public GameResult getGameResult(Dealer dealer) {
		if (isBust()) {
			return GameResult.LOSE;
		}
		if (dealer.isBust()) {
			return GameResult.WIN;
		}
		if (dealer.isBlackJack()) {
			return GameResult.LOSE;
		}
		if (isBlackJack()) {
			return GameResult.WIN;
		}
		return compareScore(dealer.getScore(), getScore());
	}

	private GameResult compareScore(int dealerScore, int playerScore) {
		if (dealerScore >= playerScore) {
			return GameResult.LOSE;
		}
		return GameResult.WIN;
	}
}

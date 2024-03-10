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

	public GameResult getGameResult(int dealerScore) {
		int playerScore = getScore();

		if (playerScore > BlackjackConstants.BLACKJACK_VALUE.getValue()) {
			return GameResult.LOSE;
		}
		if (dealerScore > BlackjackConstants.BLACKJACK_VALUE.getValue() || playerScore > dealerScore) {
			return GameResult.WIN;
		}
		return GameResult.LOSE;
	}
}

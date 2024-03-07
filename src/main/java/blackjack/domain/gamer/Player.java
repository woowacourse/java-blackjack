package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= 21;
	}

	public GameResult isWin(int dealerScore) {
		int playerScore = getScore();

		if (playerScore > 21) {
			return GameResult.LOSE;
		}
		if (dealerScore > 21 || playerScore > dealerScore) {
			return GameResult.WIN;
		}
		return GameResult.LOSE;
	}
}

package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= 21;
	}

	public boolean isWin(int dealerScore) {
		int playerScore = getScore();

		if (playerScore > 21) {
			return false;
		}
		if (dealerScore > 21) {
			return true;
		}

		return playerScore > dealerScore;
	}
}

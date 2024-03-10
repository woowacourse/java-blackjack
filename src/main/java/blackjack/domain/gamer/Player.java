package blackjack.domain.gamer;

public class Player extends BlackjackGamer {

	private static final int BLACKJACK_MAX_SCORE = 21;

	public Player(Name name) {
		super(name);
	}

	@Override
	public boolean canReceiveCard() {
		return getScore() <= BLACKJACK_MAX_SCORE;
	}

	public GameResult getGameResult(int dealerScore) {
		int playerScore = getScore();

		if (playerScore > BLACKJACK_MAX_SCORE) {
			return GameResult.LOSE;
		}
		if (dealerScore > BLACKJACK_MAX_SCORE || playerScore > dealerScore) {
			return GameResult.WIN;
		}
		return GameResult.LOSE;
	}
}

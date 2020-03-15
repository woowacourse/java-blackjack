package domain.user.strategy.draw;

public class PlayerDrawStrategy implements DrawStrategy {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_SCORE = 0;

	@Override
	public boolean canDraw(int score) {
		return score < BLACKJACK_SCORE && score != BURST_SCORE;
	}
}

package domain.user.strategy.draw;

public class DealerDrawStrategy implements DrawStrategy {
	private static final int BURST_SCORE = 0;
	private static final int DEALER_DRAW_BOUND = 17;

	@Override
	public boolean canDraw(int score) {
		return score < DEALER_DRAW_BOUND && score != BURST_SCORE;
	}
}

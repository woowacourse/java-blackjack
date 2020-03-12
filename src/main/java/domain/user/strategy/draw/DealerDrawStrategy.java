package domain.user.strategy.draw;

import domain.Score;

public class DealerDrawStrategy implements DrawStrategy {
	private static final int BURST_SCORE = 0;
	private static final int DEALER_DRAW_BOUND = 17;

	@Override
	public boolean canDraw(Score score) {
		return Score.of(DEALER_DRAW_BOUND).isBiggerThan(score) && !Score.of(BURST_SCORE).equals(score);
	}
}

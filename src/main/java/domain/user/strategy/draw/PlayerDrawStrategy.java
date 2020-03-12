package domain.user.strategy.draw;

import domain.Score;

public class PlayerDrawStrategy implements DrawStrategy {
	@Override
	public boolean canDraw(Score score) {
		return score.isNotBurst() && !score.isBlackjackScore();
	}
}

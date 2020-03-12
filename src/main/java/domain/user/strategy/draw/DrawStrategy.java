package domain.user.strategy.draw;

import domain.Score;

public interface DrawStrategy {
	boolean canDraw(Score score);
}

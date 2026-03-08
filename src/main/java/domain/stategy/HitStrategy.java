package domain.stategy;

import domain.Score.Score;

public interface HitStrategy {
    boolean needToHit(Score score);
}

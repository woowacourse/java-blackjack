package domain.hitstategy;

import domain.score.Score;

public interface HitStrategy {
    boolean needToHit(Score score);
}

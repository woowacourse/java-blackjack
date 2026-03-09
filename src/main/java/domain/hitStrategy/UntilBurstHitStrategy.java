package domain.hitStrategy;

import domain.score.Score;

public class UntilBurstHitStrategy implements HitStrategy {
    @Override
    public boolean needToHit(Score score) {
        return !score.isBurst();
    }
}

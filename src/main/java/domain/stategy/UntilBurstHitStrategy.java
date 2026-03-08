package domain.stategy;

import domain.Score.Score;

public class UntilBurstHitStrategy implements HitStrategy {
    @Override
    public boolean needToHit(Score score) {
        return !score.isBurst();
    }
}

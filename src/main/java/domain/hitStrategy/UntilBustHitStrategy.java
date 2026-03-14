package domain.hitStrategy;

import domain.score.Score;

public class UntilBustHitStrategy implements HitStrategy {
    @Override
    public boolean canHit(Score score) {
        return true;
    }
}

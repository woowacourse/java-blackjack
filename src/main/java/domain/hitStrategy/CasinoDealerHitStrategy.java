package domain.hitStrategy;

import domain.score.Score;

public class CasinoDealerHitStrategy implements HitStrategy {
    public static final int BOUNDARY = 16;

    @Override
    public boolean canHit(Score score) {
        return score.value() <= BOUNDARY;
    }
}

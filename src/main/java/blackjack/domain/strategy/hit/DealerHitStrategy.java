package blackjack.domain.strategy.hit;

import blackjack.domain.card.Score;

public class DealerHitStrategy implements HitStrategy {

    private static final Score HIT_THRESHOLD = new Score(16);

    private final Score score;

    public DealerHitStrategy(Score score) {
        this.score = score;
    }

    @Override
    public boolean isHit() {
        return !score.isGreaterThan(HIT_THRESHOLD);
    }
}

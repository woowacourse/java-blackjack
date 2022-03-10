package blackjack.domain.strategy;

import blackjack.domain.card.Score;

public class DealerHitStrategy implements HitStrategy {

    private static final Score HIT_THRESHOLD = new Score(16);

    private final Score score;

    public DealerHitStrategy(Score score) {
        this.score = score;
    }

    @Override
    public boolean isHit() {
        return HIT_THRESHOLD.isGreaterThan(score);
    }
}

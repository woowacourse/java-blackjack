package blackjack.domain.strategy.hit;

import java.util.function.Supplier;

import blackjack.domain.card.Score;

public class DealerHitStrategy implements HitStrategy {

    private static final Score HIT_THRESHOLD = new Score(16);

    private final Supplier<Score> supplier;

    public DealerHitStrategy(Supplier<Score> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean isHit() {
        return !supplier.get().isGreaterThan(HIT_THRESHOLD);
    }
}

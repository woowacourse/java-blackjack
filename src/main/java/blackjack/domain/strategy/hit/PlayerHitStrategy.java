package blackjack.domain.strategy.hit;

import java.util.function.Supplier;

public class PlayerHitStrategy implements HitStrategy {

    private Supplier<Boolean> supplier;

    public PlayerHitStrategy(Supplier<Boolean> supplier) {
        this.supplier = supplier;
    }

    @Override
    public boolean isHit() {
        return supplier.get();

    }
}

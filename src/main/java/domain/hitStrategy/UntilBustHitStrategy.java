package domain.hitStrategy;

import domain.state.Bust;
import domain.state.State;

public class UntilBustHitStrategy implements HitStrategy {
    @Override
    public boolean canHit(State state) {
        return !(state instanceof Bust);
    }
}

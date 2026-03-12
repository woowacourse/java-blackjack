package domain.hitStrategy;

import domain.state.State;
import domain.state.finished.Bust;

public class UntilBustHitStrategy implements HitStrategy {
    @Override
    public boolean canHit(State state) {
        return !(state instanceof Bust);
    }
}

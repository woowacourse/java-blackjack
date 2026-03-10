package domain.hitStrategy;

import domain.state.Burst;
import domain.state.State;

public class UntilBurstHitStrategy implements HitStrategy {
    @Override
    public boolean canHit(State state) {
        return !(state instanceof Burst);
    }
}

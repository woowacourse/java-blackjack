package domain.hitStrategy;

import domain.state.Burst;
import domain.state.State;

public class CasinoDealerHitStrategy implements HitStrategy {
    public static final int BOUNDARY = 16;

    @Override
    public boolean canHit(State state) {
        return !(state instanceof Burst) && state.getScore() <= BOUNDARY;
    }
}

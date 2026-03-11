package domain.hitStrategy;

import domain.state.Bust;
import domain.state.State;

public class CasinoDealerHitStrategy implements HitStrategy {
    public static final int BOUNDARY = 16;

    @Override
    public boolean canHit(State state) {
        return !(state instanceof Bust) && state.getScore() <= BOUNDARY;
    }
}

package domain.hitStrategy;

import domain.state.State;

public interface HitStrategy {
    boolean canHit(State state);
}

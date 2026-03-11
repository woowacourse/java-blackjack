package domain.participants;

import domain.bet.Betting;
import domain.hitStrategy.HitStrategy;
import domain.hitStrategy.UntilBustHitStrategy;
import domain.state.State;

public class Player extends Participant {
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new UntilBustHitStrategy();

    private final HitStrategy hitStrategy;

    public Player(String name, HitStrategy hitStrategy, Betting cost) {
        super(name, cost);
        this.hitStrategy = hitStrategy;
    }

    public static Player createDefaultStrategy(String name, Betting cost) {
        return new Player(name, DEFAULT_HIT_STRATEGY, cost);
    }

    @Override
    public State getStartState(Hand hand) {
        return State.getStartState(hand, this, hitStrategy);
    }

    @Override
    public HitStrategy getHitStrategy() {
        return hitStrategy;
    }
}

package domain.participants;

import domain.card.Hand;
import domain.hitStrategy.HitStrategy;
import domain.hitStrategy.UntilBurstHitStrategy;
import domain.state.Hit;
import domain.state.State;

public class Player extends Participant {
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new UntilBurstHitStrategy();

    private final HitStrategy hitStrategy;
    //아마 여기에 금액추가.

    public Player(String name, Hand hand, HitStrategy hitStrategy) {
        super(name, hand);
        this.hitStrategy = hitStrategy;
    }

    public static Player createDefaultStrategy(String name, Hand hand) {
        return new Player(name, hand, DEFAULT_HIT_STRATEGY);
    }

    @Override
    public State getStartState() {
        return new Hit(hand, this, hitStrategy);
    }

    @Override
    public HitStrategy getHitStrategy() {
        return hitStrategy;
    }
}

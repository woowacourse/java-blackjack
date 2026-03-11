package domain.participants;

import domain.bet.Betting;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.HitStrategy;
import domain.state.State;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new CasinoDealerHitStrategy();

    private final HitStrategy hitStrategy;

    public Dealer(HitStrategy hitStrategy) {
        super(NAME, new Betting(0));
        this.hitStrategy = hitStrategy;
    }

    public static Dealer createDefaultStrategy() {
        return new Dealer(DEFAULT_HIT_STRATEGY);
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

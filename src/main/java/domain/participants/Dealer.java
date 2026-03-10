package domain.participants;

import domain.card.Hand;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.HitStrategy;
import domain.state.Hit;
import domain.state.State;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new CasinoDealerHitStrategy();

    private final HitStrategy hitStrategy;

    public Dealer(Hand hand, HitStrategy hitStrategy) {
        super(NAME, hand);
        this.hitStrategy = hitStrategy;
    }

    public static Dealer createDefaultStrategy(Hand hand) {
        return new Dealer(hand, DEFAULT_HIT_STRATEGY);
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

package domain.participants;

import domain.bet.Betting;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.HitStrategy;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final Betting DEFAULT_BETTING = new Betting(0);
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new CasinoDealerHitStrategy();

    private final HitStrategy hitStrategy;

    public Dealer(Hand hand, HitStrategy hitStrategy) {
        super(NAME, hand, DEFAULT_BETTING);
        this.hitStrategy = hitStrategy;
    }

    public static Dealer createDefaultStrategy(Hand hand) {
        return new Dealer(hand, DEFAULT_HIT_STRATEGY);
    }

    @Override
    public boolean canDraw() {
        return hitStrategy.canHit(getState());
    }
}

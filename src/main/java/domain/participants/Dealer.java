package domain.participants;

import domain.card.Hand;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.HitStrategy;

//추상 클래스
public class Dealer extends Participant {
    private static final String NAME = "딜러";
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new CasinoDealerHitStrategy();


    public Dealer(Hand hand, HitStrategy hitStrategy) {
        super(NAME, hand, hitStrategy);
    }

    public static HitStrategy getDefaultHitStrategy() {
        return DEFAULT_HIT_STRATEGY;
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished() && super.hitStrategy.canHit(state);
    }
}

package domain.participants;

import domain.bet.Betting;
import domain.card.Hand;
import domain.hitStrategy.CasinoDealerHitStrategy;
import domain.hitStrategy.HitStrategy;
import domain.state.State;
import domain.state.running.Running;

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
        return !state.isFinished() && hitStrategy.canHit(state);
    }

    @Override
    public State getState() {
        if (!canDraw() && state instanceof Running) {
            stay();
        }
        return state;
    }
}

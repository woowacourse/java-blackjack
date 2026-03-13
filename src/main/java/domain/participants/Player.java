package domain.participants;

import domain.bet.Betting;
import domain.card.Hand;
import domain.hitStrategy.HitStrategy;
import domain.hitStrategy.UntilBustHitStrategy;

public class Player extends Participant {
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new UntilBustHitStrategy();

    protected final Betting betting;

    public Player(String name, Hand hand, Betting betting, HitStrategy hitStrategy) {
        super(name, hand, hitStrategy);
        this.betting = betting;
    }

    public static HitStrategy getDefaultHitStrategy() {
        return DEFAULT_HIT_STRATEGY;
    }

    public Integer getProfit(Participant dealer) {
        return state.getProfit(dealer.getState(), betting.amount());
    }

    @Override
    public boolean canDraw() {
        return hitStrategy.canHit(state);
    }
}

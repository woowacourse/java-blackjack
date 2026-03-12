package domain.participants;

import domain.bet.Betting;
import domain.hitStrategy.HitStrategy;
import domain.hitStrategy.UntilBustHitStrategy;
import domain.state.running.Hit;

public class Player extends Participant {
    private static final HitStrategy DEFAULT_HIT_STRATEGY = new UntilBustHitStrategy();

    private final HitStrategy hitStrategy;

    public Player(String name, Hand hand, Betting cost, HitStrategy hitStrategy) {
        super(name, hand, cost);
        this.hitStrategy = hitStrategy;
    }

    public static Player createDefaultStrategy(String name, Hand hand, Betting cost) {
        return new Player(name, hand, cost, DEFAULT_HIT_STRATEGY);
    }

    @Override
    protected Hit getStartState(Hand hand) {
        return Hit.getStartState(hand, hitStrategy);
    }


    @Override
    public HitStrategy getHitStrategy() {
        return hitStrategy;
    }

    public Integer getProfit(Dealer dealer) {
        return state.getProfit(super.betting.amount());
    }
}

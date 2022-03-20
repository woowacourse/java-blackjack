package blackjack.domain.player;

import blackjack.domain.state.State;

public class Guest extends AbstractPlayer implements Player {

    private static final int HIT_MAX_POINT = 21;

    private final BetMoney betMoney;

    public Guest(String name, State state, int money) {
        super(name, state);
        this.betMoney = new BetMoney(money);
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    protected int limitHit() {
        return HIT_MAX_POINT;
    }

    public BetMoney getBetMoney() {
        return betMoney;
    }
}

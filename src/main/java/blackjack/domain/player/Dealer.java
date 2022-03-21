package blackjack.domain.player;

import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

public class Dealer extends AbstractPlayer implements Player {

    private static final int HIT_MAX_POINT = 16;
    private static final String NAME = "딜러";

    public Dealer(String name, State state) {
        super(name, state);
    }

    public Dealer() {
        this(NAME, new Ready());
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    protected int limitHit() {
        return HIT_MAX_POINT;
    }
}

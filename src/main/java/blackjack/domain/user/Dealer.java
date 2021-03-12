package blackjack.domain.user;

import blackjack.domain.state.DealerTurnOver;
import blackjack.domain.state.State;

public class Dealer extends AbstractUser {
    public static final int TURN_OVER_COUNT = 16;

    private final String name = "딜러";

    public Dealer(State state) {
        super(state);
        checkTurnOver(state);
    }

    private void checkTurnOver(State state) {
        if (state.calculateScore() > TURN_OVER_COUNT) {
            changeState(new DealerTurnOver(state.cards()));
        }
    }

    @Override
    public boolean canDraw() {
        return getState().calculateScore() <= TURN_OVER_COUNT && !getState().isFinish();
    }

    @Override
    public String getName() {
        return name;
    }
}

package blackjack.domain.gamer;

import blackjack.domain.state.State;

public class Dealer extends Participants {

    private static final int DEALER_MAX_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public Dealer(State state) {
        super(DEALER_NAME, state);
    }

    @Override
    public boolean canDraw() {
        return state.score() <= DEALER_MAX_SCORE;
    }
}

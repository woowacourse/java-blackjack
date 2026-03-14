package domain.member;

import domain.state.State;

public class Dealer extends Member {
    private static final int DEALER_DRAW_CONDITION = 16;

    public Dealer(State initialState) {
        super(DEALER_NAME, initialState);
    }

    public boolean isMeetTheDrawCondition() {
        return currentScore() <= DEALER_DRAW_CONDITION;
    }
}

package domain.member;

import domain.state.State;

public class Dealer extends Member {

    public Dealer(State initialState) {
        super(DEALER_NAME, initialState);
    }
}

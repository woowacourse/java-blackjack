package blackjack.model.participant;

import blackjack.model.state.BlackjackState;

public class Dealer extends Participant {

    private static final int DRAW_UPPER_BOUND = 16;

    public Dealer() {
        super();
    }

    public Dealer(BlackjackState state) {
        super(state);
    }

    public boolean shouldDraw() {
        return state.getScore() <= DRAW_UPPER_BOUND;
    }
}

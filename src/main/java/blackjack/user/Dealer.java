package blackjack.user;

import blackjack.State;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    private Dealer() {
        super(NAME);
    }

    public static Dealer generate() {
        return new Dealer();
    }


    @Override
    protected void setStateStayIfSatisfied(boolean stayFlag) {
        if (state.equals(State.HIT) && myCards.scoreSum() > 16) {
            state = State.STAY;
        }
    }

    @Override
    protected void updateStateAfterAddCard() {
        setStateBlackjackIfSatisfied();
        setStateBustIfSatisfied();
        setStateStayIfSatisfied(true);
    }
}

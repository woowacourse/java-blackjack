package blackjack.domain.participant;

import blackjack.domain.state.Hit;

public class Dealer extends Participant{
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstCardsInfoToString() {
        return state.cards().getFirstCardInfoToString();
    }

    public void doStayIfPossible() {
        if (getCardsScore() > 17 && state instanceof Hit) {
            state = state.stay();
        }
    }
}

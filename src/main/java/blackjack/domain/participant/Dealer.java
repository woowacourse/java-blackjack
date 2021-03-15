package blackjack.domain.participant;

import blackjack.domain.state.Hit;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_STAY_LIMIT = 17;

    public Dealer() {
        super(DEALER_NAME);
    }

    public String getFirstCardsInfoToString() {
        return state.cards().getFirstCardInfoToString();
    }

    public void doStayIfPossible() {
        if (getCardsScore() >= DEALER_STAY_LIMIT && state instanceof Hit) {
            state = state.stay();
        }
    }

    @Override
    public void setUpParticipantTwoCardsAndState() {
        super.setUpParticipantTwoCardsAndState();
        if (getCardsScore() >= DEALER_STAY_LIMIT && !isBlackjack()) {
            state.stay();
        }
    }
}

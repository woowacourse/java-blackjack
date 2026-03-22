package domain.participant;

import domain.card.Hand;

public class Dealer extends Participant {
    private static final int RECEIVE_CARD_CONDITION = 16;

    public Dealer(Hand hand) {
        super(hand);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= RECEIVE_CARD_CONDITION;
    }
}

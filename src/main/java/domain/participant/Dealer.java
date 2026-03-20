package domain.participant;

import domain.card.Cards;

public class Dealer extends Participant {
    private static final int RECEIVE_CARD_CONDITION = 16;

    public Dealer(Cards cards) {
        super(cards);
    }

    public boolean canReceiveCard() {
        return calculateScore() <= RECEIVE_CARD_CONDITION;
    }
}

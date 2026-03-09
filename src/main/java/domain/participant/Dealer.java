package domain.participant;

import domain.card.Card;

public class Dealer extends Participant {
    private static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public void keepCard(Card card) {
        if (getTotalCardScore() < 17) {
            this.getHand().addCard(card);
        }
    }
}

package blackjack.domain.participant;

import blackjack.domain.card.Card;

public class Dealer extends BlackJackParticipant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public void draw(Card card) {
        getHand().addCard(card);
        updateState();
        if (isStay()) {
            stay();
        }
    }

    private boolean isStay() {
        return getScore() > DEALER_LIMIT && !isBust();
    }
}

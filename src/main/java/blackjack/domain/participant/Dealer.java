package blackjack.domain.participant;

import blackjack.domain.Deck;

public class Dealer extends Participant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(Deck deck) {
        super(DEALER_NAME, deck);
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().addCard(deck.draw());
        if (isOverLimit()) {
            cannotDraw();
        }
    }

    private boolean isOverLimit() {
        return getHand().getScore() > DEALER_LIMIT;
    }
}

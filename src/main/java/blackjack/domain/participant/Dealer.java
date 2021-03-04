package blackjack.domain.participant;

import blackjack.domain.Deck;
import blackjack.domain.Hand;

public class Dealer extends BlackJackParticipant {

    public static final int DEALER_LIMIT = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }
    public Dealer(Hand hand) {
        super(DEALER_NAME, hand);
    }

    @Override
    public void drawCard(Deck deck) {
        getHand().addCard(deck.draw());
        if (isOverLimit()) {
            cannotDraw();
        }
    }

    private boolean isOverLimit() {
        return getScore() > DEALER_LIMIT;
    }
}

package blackjack.domain.participant;

import blackjack.domain.Deck;

public class Dealer extends BlackJackParticipant{

    private static final int DEALER_LIMIT = 16;

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

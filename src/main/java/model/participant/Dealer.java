package model.participant;

import model.card.Card;
import model.deck.Deck;

public class Dealer extends Participant {
    private static final int DEALER_DRAW_THRESHOLD = 16;

    public Dealer(Deck deck) {
        super();
        drawInitialCards(deck);
    }

    private void drawInitialCards(Deck deck) {
        receiveCard(deck.getCard());
        receiveCard(deck.getCard());

        while (canDrawMore()) {
            receiveCard(deck.getCard());
        }
    }

    @Override
    protected boolean canDrawMore() {
        return calculateScore() <= DEALER_DRAW_THRESHOLD;
    }

    public Card getFirstCard() {
        return cards.getFirstCard();
    }
}

package model.cards;

import java.util.ArrayList;
import model.deck.Deck;

public class DealerCardsFactory {

    private static final int DEALER_DRAW_THRESHOLD = 16;

    public static Cards generate(final Deck deck) {
        Cards cards = new Cards(new ArrayList<>());
        while (cards.calculateResult() <= DEALER_DRAW_THRESHOLD) {
            cards.addCard(deck.getCard());
        }
        return cards;
    }
}

package model;

import java.util.ArrayList;

public class DealerCardsFactory {

    private static final int DEALER_DRAW_THRESHOLD = 16;

    public static Cards generate(final Deck deck) {
        Cards cards = new Cards(new ArrayList<>());
        while (true) {
            cards.addCard(deck.getCard());
            if (cards.calculateResult() > DEALER_DRAW_THRESHOLD) {
                return cards;
            }
        }
    }
}

package model;

import java.util.HashSet;

public class PlayerCardsFactory {

    public static Cards generate(final Deck deck) {
        Cards cards = new Cards(new HashSet<>());
        cards.addCard(deck.getCard());
        cards.addCard(deck.getCard());
        return cards;
    }
}

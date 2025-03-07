package model;

import java.util.ArrayList;
import java.util.HashSet;

public class PlayerCardsFactory {

    public static Cards generate(final Deck deck) {
        Cards cards = new Cards(new ArrayList<>());
        cards.addCard(deck.getCard());
        cards.addCard(deck.getCard());
        return cards;
    }
}

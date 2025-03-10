package model.cards;

import java.util.ArrayList;
import model.deck.Deck;

public class PlayerCardsFactory {

    public static Cards generate(final Deck deck) {
        Cards cards = new Cards(new ArrayList<>());
        cards.addCard(deck.getCard());
        cards.addCard(deck.getCard());
        return cards;
    }
}

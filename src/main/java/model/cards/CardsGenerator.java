package model.cards;

import java.util.ArrayList;
import java.util.List;
import model.card.Card;
import model.deck.Deck;

public abstract class CardsGenerator {

    public Cards generate(final Deck deck) {
        List<Card> cards = new ArrayList<>();
        cards.add(deck.getCard());
        cards.add(deck.getCard());
        return createCards(deck, cards);
    }

    abstract Cards createCards(final Deck deck, final List<Card> cards);
}

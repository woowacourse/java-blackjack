package domain;

import java.util.Collections;
import java.util.Stack;

import factory.CardFactory;

public class CardDeck {
    private Stack<Card> cardDeck = new Stack<>();

    public CardDeck() {
        cardDeck.addAll(CardFactory.create());
    }

    public void shuffle() {
        Collections.shuffle(cardDeck);
    }

    public Card drawOne() {
        return cardDeck.pop();
    }
}

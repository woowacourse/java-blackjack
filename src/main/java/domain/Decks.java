package domain;

import domain.card.Card;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Decks {
    private final Stack<Card> cards;

    private Decks(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Decks createByStrategy(DecksGenerator decksGenerator) {
        Stack<Card> cards = decksGenerator.generate();
        return new Decks(cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

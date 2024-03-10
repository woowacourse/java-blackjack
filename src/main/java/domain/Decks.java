package domain;

import domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Decks {
    private static final String NO_CARDS = "카드가 모두 소진되었습니다.";
    private final Stack<Card> cards;

    private Decks(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Decks createByStrategy(final DecksGenerator decksGenerator) {
        Stack<Card> cards = decksGenerator.generate();
        return new Decks(cards);
    }

    public Card draw() {
        if (cards.empty()) {
            throw new IllegalArgumentException(NO_CARDS);
        }
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

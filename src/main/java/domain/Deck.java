package domain;

import domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Deck {
    private static final String NO_CARDS = "카드가 모두 소진되었습니다.";
    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck createByStrategy(final DeckGenerator deckGenerator) {
        Stack<Card> cards = deckGenerator.generate().stream()
                .collect(Collectors.toCollection(Stack::new));
        return new Deck(cards);
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

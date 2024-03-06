package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Decks {
    private static final int DECK_COUNT = 6;
    private final Stack<Card> cards;

    public Decks() {
        Stack<Card> decks = Stream.generate(Deck::new)
                .limit(DECK_COUNT)
                .flatMap(deck -> deck.getCards().stream())
                .collect(Collectors.toCollection(Stack::new));
        Collections.shuffle(decks);
        this.cards = decks;
    }

    public Card draw() {
        return cards.pop();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}

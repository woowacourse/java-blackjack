package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final Stack<Card> cards;

    public Deck() {
        Stack<Card> deck = Stream.of(Symbol.values())
                .flatMap(symbol -> Stream.of(Number.values())
                        .map(number -> new Card(symbol, number)))
                .collect(Collectors.toCollection(Stack::new));
        Collections.shuffle(deck);
        this.cards = deck;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card draw() {
        return cards.pop();
    }
}

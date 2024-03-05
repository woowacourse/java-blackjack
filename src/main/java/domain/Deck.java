package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        List<Card> deck = Stream.of(Symbol.values())
                .flatMap(symbol -> Stream.of(Number.values()).map(number -> new Card(symbol, number)))
                .collect(Collectors.toList());
        Collections.shuffle(deck);
        this.cards = List.copyOf(deck);
    }

    public List<Card> getCards() {
        return cards;
    }
}

package domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {
    private final List<Card> cards;

    public Deck() {
        List<Card> deck = Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> new Card(symbol, rank)))
                .collect(Collectors.toList());
        this.cards = deck;
    }

    public List<Card> getCards() {
        return cards;
    }
}

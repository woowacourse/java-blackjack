package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {
    private static final List<Card> cards = new ArrayList<>();

    static {
        cards.addAll(Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .collect(Collectors.toList()));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

}

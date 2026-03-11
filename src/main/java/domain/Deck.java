package domain;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck create() {
        return new Deck(
                Arrays.stream(Suit.values())
                        .flatMap(suit -> Arrays.stream(Rank.values())
                                .map(rank -> new Card(suit, rank)))
                        .collect(Collectors.toCollection(ArrayList::new))
        );
    }

    public Card pop() {
        Card card = cards.getLast();
        cards.removeLast();

        return card;
    }

    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}

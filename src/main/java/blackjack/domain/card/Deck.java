package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> deck;

    private Deck(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
    }

    public Card drawCard() {
        return deck.remove(0);
    }

    public static Deck createShuffledDeck() {
        List<Card> deck = createDeck();
        Collections.shuffle(deck);

        return new Deck(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> new Card(suit, rank)))
                .collect(Collectors.toList());
    }
}

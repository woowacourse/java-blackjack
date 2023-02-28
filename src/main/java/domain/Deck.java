package domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private final List<Card> deck;

    public Deck() {
        this.deck = createDeck();
        Collections.shuffle(deck);
    }

    private List<Card> createDeck() {
        return Arrays.stream(Denomination.values())
                .flatMap(
                        denomination -> Arrays.stream(Suit.values())
                                .map(suit -> new Card(suit, denomination))
                )
                .collect(Collectors.toList());
    }
}

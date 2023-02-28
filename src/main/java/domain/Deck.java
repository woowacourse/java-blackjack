package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private static final List<Card> deck;
    private static int index = 0;

    static {
        deck = createDeck();
        Collections.shuffle(deck);
    }

    private static List<Card> createDeck() {
        return Arrays.stream(Denomination.values())
                .flatMap(
                        denomination -> Arrays.stream(Suit.values())
                                .map(suit -> new Card(suit, denomination))
                )
                .collect(Collectors.toList());
    }

    public static Card pickCard() {
        return deck.get(index++);
    }

    public static List<Card> getDeck() {
        return new ArrayList<>(deck);
    }
}

package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<Card> deck = new ArrayList<>();

    public Deck() {
        Arrays.stream(Denomination.values())
                .forEach(value -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(suit, value))
                        .forEach(deck::add));
        Collections.shuffle(deck);
    }
}

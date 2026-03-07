package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Trump {

    private final List<Card> deck;

    public Trump() {
        deck = generateShuffledDeck();
    }

    private List<Card> generateShuffledDeck() {
        List<Card> shuffledDeck = Arrays.stream(Suit.values())
            .flatMap(suit -> Arrays.stream(Denomination.values())
                .map(denomination -> new Card(suit, denomination)))
            .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(shuffledDeck);

        return shuffledDeck;
    }

    public Card draw() {
        return deck.removeLast();
    }
}

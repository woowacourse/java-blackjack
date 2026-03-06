package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Trump {

    private final List<Card> deck;

    public Trump() {
        deck = generateShuffledDeck();
    }

    private List<Card> generateShuffledDeck() {
        List<Suit> suits = Suit.all();
        List<Denomination> denominations = Denomination.all();
        List<Card> shuffledDeck = suits.stream()
            .flatMap(item1 -> denominations.stream()
                .map(item2 -> new Card(item1, item2)))
            .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(shuffledDeck);

        return shuffledDeck;
    }

    public Card draw() {
        return deck.removeLast();
    }
}

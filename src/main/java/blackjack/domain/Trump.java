package blackjack.domain;

import blackjack.utils.Lists;
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
        final List<Card> shuffledDeck =
            Lists.cartesianProduct(List.of(Suit.values()), List.of(Denomination.values()))
            .stream()
            .map(pair -> new Card(pair.getLeft(), pair.getRight()))
            .collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(shuffledDeck);

        return shuffledDeck;
    }

    public Card draw() {
        return deck.removeLast();
    }
}

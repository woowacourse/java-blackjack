package blackjack.domain;

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

        return suits.stream()
            .flatMap(item1 -> denominations.stream()
                .map(item2 -> new Card(item1, item2)))
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    Collections.shuffle(list);
                    return list;
                }
            ));
    }

    public Card draw() {
        return deck.removeLast();
    }
}

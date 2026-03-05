package blackjack.domain;

import java.util.List;

public class Trump {

    private final List<Card> deck;

    public Trump() {
        deck = generateDeck();
    }

    private List<Card> generateDeck() {
        List<Suit> suits = Suit.all();
        List<Denomination> denominations = Denomination.all();

        return suits.stream()
            .flatMap(item1 -> denominations.stream()
                .map(item2 -> new Card(item1, item2)))
            .toList();

    }
}

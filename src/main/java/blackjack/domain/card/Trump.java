package blackjack.domain.card;

import blackjack.strategy.ShuffleStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Trump {

    private final List<Card> deck;

    public Trump(ShuffleStrategy shuffleStrategy) {
        deck = generateInitDeck();
        shuffleStrategy.shuffle(deck);
    }

    public Card draw() {
        return deck.removeLast();
    }

    private List<Card> generateInitDeck() {
        List<Suit> suits = Suit.all();
        List<Denomination> denominations = Denomination.all();

        return suits.stream()
                .flatMap(suit -> denominations.stream()
                        .map(denomination -> new Card(suit, denomination)))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

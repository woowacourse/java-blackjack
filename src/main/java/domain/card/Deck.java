package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Deck {

    private static final int INIT_INDEX = 0;
    private final List<Card> deck;

    private int index;

    public Deck() {
        this.deck = createDeck();
        index = INIT_INDEX;
        Collections.shuffle(deck);
    }

    private List<Card> createDeck() {
        return Arrays.stream(Denomination.values())
                .flatMap(generateCardForm())
                .collect(Collectors.toList());
    }

    private Function<Denomination, Stream<? extends Card>> generateCardForm() {
        return denomination -> Arrays.stream(Suit.values())
                .map(suit -> new Card(suit, denomination));
    }

    public Card pickCard() {
        return deck.get(index++);
    }

    public List<Card> getDeck() {
        return new ArrayList<>(deck);
    }
}

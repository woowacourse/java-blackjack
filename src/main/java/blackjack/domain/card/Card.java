package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> PLAYING_CARDS;

    private final String name;
    private final Denomination value;

    static {
        PLAYING_CARDS = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Denomination.values())
                            .map(denomination -> Card.of(suit, denomination))
                )
                .collect(Collectors.toList());
    }

    private Card(final Suit symbol, final Denomination value) {
        this.value = value;
        this.name = value.getName() + symbol.getSymbol();
    }

    public static Card of(final Suit symbol, final Denomination value) {
        return new Card(symbol, value);
    }

    public static List<Card> createDeck() {
        return new ArrayList<>(PLAYING_CARDS);
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value.getValue();
    }

    public Denomination getCardValue() {
        return value;
    }
}
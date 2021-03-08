package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Card {

    private static final List<Card> PLAYING_CARDS;

    private final String name;
    private final CardValue value;

    static {
        PLAYING_CARDS = Arrays.stream(CardSymbol.values())
                .flatMap(cardSymbol -> Arrays.stream(CardValue.values())
                            .map(cardValue -> Card.of(cardSymbol, cardValue))
                )
                .collect(Collectors.toList());
    }

    private Card(final CardSymbol symbol, final CardValue value) {
        this.value = value;
        this.name = value.getName() + symbol.getSymbol();
    }

    public static Card of(final CardSymbol symbol, final CardValue value) {
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

    public CardValue getCardValue() {
        return value;
    }
}
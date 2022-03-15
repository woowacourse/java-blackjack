package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Card {

    private static final Map<String, Card> cache;

    static {
        cache = Arrays.stream(CardSymbol.values())
                .flatMap(symbol -> Arrays.stream(CardNumber.values())
                        .map(number -> new Card(symbol, number))
                        .collect(Collectors.toList())
                        .stream())
                .collect(Collectors.toMap(
                        card -> findKey(card.symbol, card.number),
                        card -> card,
                        (a, b) -> a));
    }

    private final CardSymbol symbol;
    private final CardNumber number;

    private Card(CardSymbol symbol, CardNumber number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card of(CardSymbol symbol, CardNumber number) {
        return cache.get(findKey(symbol, number));
    }

    private static String findKey(CardSymbol symbol, CardNumber number) {
        return symbol.getName() + number.getName();
    }

    public static List<Card> getAllCards() {
        return new ArrayList<>(cache.values());
    }

    public boolean isAce() {
        return number == CardNumber.ACE;
    }

    public int getNumberValue() {
        return number.getValue();
    }

    public String getNumberName() {
        return number.getName();
    }

    public String getSymbolName() {
        return symbol.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Card card = (Card) o;
        return symbol == card.symbol && number == card.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, number);
    }

    @Override
    public String toString() {
        return "Card{" +
                "symbol=" + symbol +
                ", number=" + number +
                '}';
    }
}

package blackjack.domain.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Card {

    private static final Map<Symbol, Map<Number, Card>> cache = new HashMap<>();

    static {
        List<Symbol> symbols = Symbol.getAll();
        for (Symbol symbol : symbols) {
            cache.put(symbol, createAllNumberCard(symbol));
        }
    }

    private final Symbol symbol;
    private final Number number;

    private Card(Symbol symbol, Number number) {
        this.symbol = symbol;
        this.number = number;
    }

    public static Card of(Symbol symbol, Number number) {
        return cache.get(symbol).get(number);
    }

    private static Map<Number, Card> createAllNumberCard(Symbol symbol) {
        List<Number> numbers = Number.getAll();
        Map<Number, Card> numberCard = new HashMap<>();
        for (Number number : numbers) {
            numberCard.put(number, new Card(symbol, number));
        }
        return numberCard;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Number getNumber() {
        return number;
    }
}

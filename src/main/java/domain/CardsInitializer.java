package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardsInitializer {

    public Cards initialize() {
        List<Card> cards = createCards();
        Collections.shuffle(cards);
        return new Cards(cards);
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        List<Number> numbers = getNumbers();
        List<Symbol> symbols = getSymbols();

        for (Number number : numbers) {
            for (Symbol symbol : symbols) {
                cards.add(new Card(symbol, number));
            }
        }

        return cards;
    }

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }

    private List<Number> getNumbers() {
        return Arrays.stream(Number.values())
                .filter(number -> !number.equals(Number.SOFT_ACE))
                .toList();
    }
}

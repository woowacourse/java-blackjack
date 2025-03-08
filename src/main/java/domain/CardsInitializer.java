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

        List<Rank> numbers = getNumbers();
        List<Symbol> symbols = getSymbols();

        insertCards(numbers, symbols, cards);

        return cards;
    }

    private void insertCards(List<Rank> numbers, List<Symbol> symbols, List<Card> cards) {
        for (Rank number : numbers) {
            for (Symbol symbol : symbols) {
                cards.add(new Card(symbol, number));
            }
        }
    }

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }

    private List<Rank> getNumbers() {
        return Arrays.stream(Rank.values())
                .filter(number -> !number.equals(Rank.SOFT_ACE))
                .toList();
    }
}

package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CardsInitializer {

    public List<Card> initialize() {
        List<Card> cards = createCards();
        return cardsShuffle(cards);
    }

    private List<Card> createCards() {
        List<Rank> numbers = getNumbers();
        List<Symbol> symbols = getSymbols();

        return symbols.stream()
                .flatMap(symbol ->
                        numbers.stream()
                                .map(number -> new Card(symbol, number)))
                .toList();
    }

    private List<Card> cardsShuffle(List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        Collections.shuffle(shuffleCards);
        return shuffleCards;
    }

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }

    private List<Rank> getNumbers() {
        return Arrays.stream(Rank.values()).filter(number -> !number.equals(Rank.SOFT_ACE)).toList();
    }
}

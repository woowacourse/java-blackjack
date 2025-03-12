package domain;

import java.util.Arrays;
import java.util.List;

public class CardsInitializer {

    private final CardShuffler cardShuffler;

    public CardsInitializer(final CardShuffler cardShuffler) {
        this.cardShuffler = cardShuffler;
    }

    public List<Card> initialize() {
        List<Card> cards = createCards();
        cardShuffler.shuffle(cards);

        return cards;
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

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }

    private List<Rank> getNumbers() {
        return Arrays.stream(Rank.values()).filter(number -> !number.equals(Rank.SOFT_ACE)).toList();
    }
}

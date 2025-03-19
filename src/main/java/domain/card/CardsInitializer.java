package domain.card;

import domain.card.shufflestrategy.ShuffleStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardsInitializer {

    private final ShuffleStrategy shuffler;

    public CardsInitializer(final ShuffleStrategy shuffler) {
        this.shuffler = shuffler;
    }

    public List<Card> initialize() {
        List<Card> cards = createCards();
        return shuffleCards(cards);
    }

    private List<Card> shuffleCards(final List<Card> cards) {
        List<Card> shuffleCards = new ArrayList<>(cards);
        shuffler.shuffle(shuffleCards);

        return shuffleCards;
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

    private List<Rank> getNumbers() {
        return Arrays.stream(Rank.values())
                .filter(number -> !number.equals(Rank.SOFT_ACE))
                .toList();
    }

    private List<Symbol> getSymbols() {
        return Arrays.stream(Symbol.values()).toList();
    }
}

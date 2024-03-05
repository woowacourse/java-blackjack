package domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import strategy.CardGenerator;

public class SequentialCardGenerator implements CardGenerator {

    private final List<Card> cards;

    private SequentialCardGenerator(List<Card> cards) {
        this.cards = cards;
    }

    public static SequentialCardGenerator initialize() {
        List<Card> initializedCards = Arrays.stream(Rank.values())
            .map(SequentialCardGenerator::generateCards)
            .flatMap(List::stream)
            .collect(Collectors.toList());
        return new SequentialCardGenerator(initializedCards);
    }

    private static List<Card> generateCards(Rank rank) {
        return Arrays.stream(Symbol.values())
            .map(symbol -> new Card(rank, symbol))
            .toList();
    }

    @Override
    public boolean hasNext() {
        return !cards.isEmpty();
    }

    @Override
    public Card nextCard() {
        return cards.remove(cards.size() - 1);
    }
}

package strategy;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomCardGenerator implements CardGenerator {

    private final List<Card> cards;

    private RandomCardGenerator(List<Card> cards) {
        this.cards = cards;
    }

    public static RandomCardGenerator initialize() {
        List<Card> initializedCards = Arrays.stream(Rank.values())
            .map(RandomCardGenerator::generateCards)
            .flatMap(List::stream)
            .collect(Collectors.toList());
        Collections.shuffle(initializedCards);
        return new RandomCardGenerator(initializedCards);
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

package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ShuffledCardsGenerator {
    private static final int DUPLICATES_COUNT = 6;

    public List<Card> generate() {
        List<Card> cards = generateBlackJackCards();
        Collections.shuffle(cards);
        return cards;
    }

    private List<Card> generateBlackJackCards() {
        List<Card> cards = generateCardsOfOnePack();

        return IntStream.range(0, DUPLICATES_COUNT)
                .mapToObj(i -> cards)
                .flatMap(List::stream)
                .toList();
    }

    private List<Card> generateCardsOfOnePack() {
        return Stream.of(Symbol.values())
                .flatMap(symbol -> Rank.getRanks().stream()
                        .map(rank -> new Card(symbol, rank)))
                .toList();
    }
}

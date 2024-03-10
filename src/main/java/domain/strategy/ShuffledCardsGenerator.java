package domain.strategy;

import domain.CardsGenerator;
import domain.card.Card;
import domain.card.Rank;
import domain.card.Symbol;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledCardsGenerator implements CardsGenerator {
    private static final int DUPLICATES_COUNT = 6;

    @Override
    public List<Card> generate() {
        List<Card> cards = Stream.generate(() ->
                        Stream.of(Symbol.values())
                                .flatMap(symbol -> Rank.getRanks().stream()
                                        .map(rank -> new Card(symbol, rank)))
                ).limit(DUPLICATES_COUNT)
                .flatMap(Function.identity())
                .collect(Collectors.toList());
        Collections.shuffle(cards);
        return cards;
    }
}

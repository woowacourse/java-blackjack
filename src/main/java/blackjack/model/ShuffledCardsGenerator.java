package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShuffledCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> create() {
        List<Card> cards = Arrays.stream(Rank.values())
            .flatMap(ShuffledCardsGenerator::createCardsByRank)
            .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(cards);
        return cards;
    }

    private static Stream<Card> createCardsByRank(Rank rank) {
        return Arrays.stream(Suit.values())
            .map(suit -> new Card(rank, suit));
    }
}

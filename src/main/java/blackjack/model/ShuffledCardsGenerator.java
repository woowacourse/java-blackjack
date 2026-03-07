package blackjack.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffledCardsGenerator implements CardsGenerator {

    @Override
    public List<Card> create() {
        return Arrays.stream(Rank.values())
            .flatMap(rank -> Arrays.stream(Suit.values())
                .map(suit -> new Card(rank, suit)))
            .collect(Collectors.toCollection(ArrayList::new));
    }
}

package blackjack.domain;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Dealer extends Person {
    public Dealer() {
        super("딜러");
    }

    public Cards createUniqueCards() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Stream.of(new Card(suit, rank)))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }
}

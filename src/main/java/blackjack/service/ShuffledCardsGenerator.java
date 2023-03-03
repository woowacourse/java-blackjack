package blackjack.service;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class ShuffledCardsGenerator implements CardsGenerator {

    @Override
    public Cards generate() {
        List<Card> cards = Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Stream.of(new Card(suit, rank)))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }
}

package blackjack.service;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.Collections;
import java.util.List;

public class ShuffledCardsGenerator implements CardsGenerator {

    @Override
    public Cards generate() {
        List<Card> cards = Rank.stream()
                .flatMap(rank -> Suit.stream()
                        .map(suit -> new Card(suit, rank))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Cards(cards);
    }
}

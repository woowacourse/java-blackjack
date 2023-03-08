package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Rank;
import blackjack.domain.Suit;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckGenerator implements DeckGenerator {

    @Override
    public Deck generate() {
        List<Card> cards = Rank.stream()
                .flatMap(rank -> Suit.stream()
                        .map(suit -> Card.of(suit, rank))
                ).collect(toList());
        Collections.shuffle(cards);
        return new Deck(new ArrayDeque<>(cards));
    }
}

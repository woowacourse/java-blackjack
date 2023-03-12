package blackjack.domain.card;

import static java.util.stream.Collectors.toList;

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

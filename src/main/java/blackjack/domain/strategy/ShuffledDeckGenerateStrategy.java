package blackjack.domain.strategy;

import static blackjack.domain.card.Denomination.values;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class ShuffledDeckGenerateStrategy implements DeckGenerateStrategy {

    @Override
    public Deque<Card> generate() {
        List<Card> cards = stream(Suit.values())
                .flatMap(suit -> stream(values())
                        .map(denomination -> new Card(suit, denomination)))
                .collect(toList());

        Collections.shuffle(cards);

        return new ArrayDeque<>(cards);
    }
}

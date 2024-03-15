package blackjack.domain.deck;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffledDeckCreateStrategy implements DeckCreateStrategy{
    private static final List<Card> CACHE_DECK = Arrays.stream(Kind.values())
            .flatMap(kind -> Arrays.stream(Value.values())
                    .map(value -> new Card(kind, value)))
            .collect(Collectors.toList());

    @Override
    public Deque<Card> createDeck() {
        Collections.shuffle(CACHE_DECK);
        return new ArrayDeque<>(CACHE_DECK);
    }
}

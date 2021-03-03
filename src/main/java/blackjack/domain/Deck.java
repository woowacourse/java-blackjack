package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static final int START_COUNT = 0;

    private final Deque<Card> deck;

    public Deck(List<Card> cards) {
        shuffle(cards);
        this.deck =new ArrayDeque<>(cards);
    }

    private void shuffle(List<Card> cards) {
        Collections.shuffle(cards);
    }


    public List<Card> pop(GameStatus gameStatus) {
        int endCount = 1;
        if (gameStatus.isStart()) {
            endCount = 2;
        }
        return IntStream.range(START_COUNT, endCount)
                .mapToObj(c -> deck.pop())
                .collect(Collectors.toList());
    }
}

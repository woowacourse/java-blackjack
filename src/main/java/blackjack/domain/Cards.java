package blackjack.domain;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 52개 카드 --> 무작위 분배용 Dummy Card

public class Cards {
    private static final int START_COUNT = 0;

    private final Deque<Card> cards;

    public Cards(List<Card> cards) {
        shuffle(cards);
        this.cards =new ArrayDeque<>(cards);
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
                .mapToObj(c -> cards.pop())
                .collect(Collectors.toList());
    }

    public int size() {
        return cards.size();
    }
}

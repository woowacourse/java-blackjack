package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> shuffledCards;

    public Deck(List<Card> shuffledCards) {
        this.shuffledCards = new ArrayDeque<>(shuffledCards);
    }

    public Card drawCard() {
        if (shuffledCards.isEmpty()) {
            throw new IllegalArgumentException("뽑을 카드가 없습니다.");
        }
        return shuffledCards.pollLast();
    }
}

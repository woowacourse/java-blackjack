package blackjack.domain.card;

import blackjack.domain.rule.HandInitializer;
import blackjack.domain.rule.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException("카드를 추가할 수 없습니다.");
        }
        hand.add(card);
    }

    public boolean isBusted() {
        return getScore().isBusted();
    }

    public boolean isBlackJack() {
        return hand.size() == HandInitializer.INITIAL_HAND_SIZE && getScore().isBlackJack();
    }

    public Score getScore() {
        return Score.calculateScore(hand);
    }

    public List<Card> getCards() {
        return hand;
    }

    public Card getFirstCard() {
        if (hand.isEmpty()) {
            throw new IndexOutOfBoundsException("카드가 없습니다.");
        }

        return hand.get(0);
    }
}

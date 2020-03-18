package blackjack.domain.card;

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

    public Score getScore() {
        return Score.calculateScore(hand);
    }

    public List<Card> getCardStatus() {
        return hand;
    }
}

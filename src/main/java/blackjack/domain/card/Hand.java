package blackjack.domain.card;

import blackjack.domain.rule.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {

    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> hand = new ArrayList<>();

    public void add(Card card) {
        if (Objects.isNull(card)) {
            throw new IllegalArgumentException("카드가 입력되지 않았습니다.");
        }
        hand.add(card);
    }

    public int calculate() {
        Score score = sumScore();
        return score.getScore();
    }

    public boolean isBusted() {
        Score score = sumScore();
        return score.isBusted();
    }

    public boolean isBlackJack() {
        Score score = sumScore();
        return score.isMaxValue() && hand.size() == BLACKJACK_CARD_SIZE;
    }

    private Score sumScore() {
        return Score.from(hand);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public List<Card> getFirstHand() {
        return Collections.unmodifiableList(hand.subList(0, 1));
    }
}

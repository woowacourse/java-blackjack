package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    public static final int INITIAL_CARD_SIZE = 2;
    public static final Score BLACKJACK_SCORE = new Score(21);
    public static final Score ZERO_SCORE = new Score(0);

    private final List<Card> cards;

    public Hand(final Card... cards) {
        this(List.of(cards));
    }

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Score score() {
        Score score = ZERO_SCORE;
        for (final Card card : cards) {
            score = addScore(card, score);
        }
        return score;
    }

    private Score addScore(final Card card, final Score score) {
        if (card.isAce()) {
            return score.addAce();
        }
        return score.add(card.score());
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isInitUnfinished() {
        return size() < INITIAL_CARD_SIZE;
    }

    public boolean isBlackjack() {
        return size() == 2 && score().equals(BLACKJACK_SCORE);
    }

    public boolean isBust() {
        return score().toInt() > BLACKJACK_SCORE.toInt();
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        if (cards == null) {
            throw new IllegalStateException("손패가 초기화되지 않았습니다.");
        }
        return cards.size();
    }
}

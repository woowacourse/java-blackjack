package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACKJACK_CONDITION = 21;

    private final List<Card> cards;

    public Hand(final Card... cards) {
        this(List.of(cards));
    }

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Score score() {
        Score score = new Score(0);
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

    public void addAll(final List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return score().toInt() > BLACKJACK_CONDITION;
    }

    public boolean isNotBust() {
        return score().toInt() <= BLACKJACK_CONDITION;
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

package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Hand {
    private static final int ACE_LOW = 1;
    private static final int ACE_HIGH = 11;
    private static final int BLACKJACK_CONDITION = 21;

    private final List<Card> cards;

    public Hand(final Card... cards) {
        this(List.of(cards));
    }

    public Hand(final List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public int score() {
        int score = 0;
        for (final Card card : cards) {
            score += determineScore(card, score);
        }
        return score;
    }

    private int determineScore(final Card card, final int score) {
        if (card.isAce()) {
            return determineAceScore(score);
        }
        return card.score();
    }

    private int determineAceScore(final int score) {
        if (score + ACE_HIGH <= BLACKJACK_CONDITION) {
            return ACE_HIGH;
        }
        return ACE_LOW;
    }

    public void addAll(final List<Card> initialCards) {
        cards.addAll(initialCards);
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return score() > BLACKJACK_CONDITION;
    }

    public boolean isNotBust() {
        return score() <= BLACKJACK_CONDITION;
    }

    public List<Card> cards() {
        return Collections.unmodifiableList(cards);
    }
}

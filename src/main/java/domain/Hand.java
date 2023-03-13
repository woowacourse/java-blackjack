package domain;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Hand {
    public static final int ADDITIONAL_SCORE_OF_ACE = 10;
    private static final int BUST_LOWER_BOUND = 22;
    private static final int BLACKJACK_SCORE = 21;

    private final Deque<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBlackjack() {
        return calculateScore() == BLACKJACK_SCORE && cards.size() == 2;
    }

    public boolean isBust() {
        return calculateScore() >= BUST_LOWER_BOUND;
    }

    public int calculateScore() {
        final int score = cards.stream()
                               .mapToInt(Card::score)
                               .sum();
        return checkScoreIfHasAce(score);
    }

    private int checkScoreIfHasAce(final int score) {
        if (hasAce() && score + ADDITIONAL_SCORE_OF_ACE < BUST_LOWER_BOUND) {
            return score + ADDITIONAL_SCORE_OF_ACE;
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream()
                    .anyMatch(Card::isAce);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }
}

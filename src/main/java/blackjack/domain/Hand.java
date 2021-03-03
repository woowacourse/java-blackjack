package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hand {

    private static final int DEFAULT_SCORE = 0;
    private static final int BUST = 22;
    private final Set<Card> hand;

    public Hand(List<Card> cards) {
        hand = new HashSet<>(cards);
    }

    public int getScore() {
        return calculateScore(new LinkedList<>(hand), DEFAULT_SCORE);
    }

    private int calculateScore(List<Card> leftCards, int currentScore) {
        if (leftCards.isEmpty()) {
            return currentScore;
        }

        List<Integer> scores = leftCards.get(0).getScores();
        List<Card> cardsTail = leftCards.subList(1, leftCards.size());

        return findOptimalScore(scores, cardsTail, currentScore);
    }

    private int findOptimalScore(List<Integer> scores, List<Card> cardsTail, int currentScore) {
        return scores.stream()
            .sorted(Collections.reverseOrder())
            .map(score -> calculateScore(cardsTail, currentScore + score))
            .filter(totalScore -> totalScore < BUST)
            .findFirst()
            .orElse(calculateScore(cardsTail, currentScore + Collections.min(scores)));
    }
}

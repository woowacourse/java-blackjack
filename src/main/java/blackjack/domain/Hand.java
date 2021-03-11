package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BLACKJACK_NUMBER = 21;
    private static final int DEFAULT_SCORE = 0;
    private final List<Card> hand;

    public Hand(List<Card> cards) {
        hand = new ArrayList<>(cards);
    }

    public int getScore() {
        return calculateScore(new ArrayList<>(hand), DEFAULT_SCORE);
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
                .filter(totalScore -> totalScore <= BLACKJACK_NUMBER)
                .findFirst()
                .orElse(calculateScore(cardsTail, currentScore + Collections.min(scores)));
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> unwrap() {
        return new ArrayList<>(hand);
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_NUMBER;
    }

    public boolean isBlackJack() {
        return getScore() == BLACKJACK_NUMBER;
    }
}

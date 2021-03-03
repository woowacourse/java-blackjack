package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Hand {
    private final static int BUST = 22;
    private final Set<Card> hand;

    public Hand(List<Card> cards) {
        hand = new HashSet<>(cards);
    }

    public int getScore() {
        return calculateScore(new LinkedList<>(hand), 0);
    }

    private int calculateScore(List<Card> leftCards, int currentScore) {
        if (leftCards.isEmpty()) {
            return currentScore;
        }

        List<Integer> scores = leftCards.get(0).getScores();
        Collections.sort(scores, Collections.reverseOrder());

        int resultScore = 0;
        for (int score : scores) {
            resultScore = calculateScore(leftCards.subList(1, leftCards.size()), currentScore + score);
            if (resultScore < BUST) {
                return resultScore;
            }
        }

        return resultScore;
    }

//    public int calculateMyScore() {
//        int scoreSum = 0;
//        for (Card card : hand) {
//            List<Integer> values = card.getScores();
//            Collections.sort(values, Collections.reverseOrder());
//            int score = card.getScores().stream()
//                .filter(value -> scoreSum + value < BUST)
//                .findFirst()
//                .orElse;
//        }
//    }
}

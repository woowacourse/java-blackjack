package blackjack.domain.card;

import static blackjack.domain.card.BlackjackConstants.BUST_THRESHOLD;

import java.util.List;
import java.util.Set;

public class ScoreCalculator {

    public int calculateMaxScore(List<Card> cards) {
        return maxDfs(0, 0, cards);
    }

    private int maxDfs(int depth, int totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        Set<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            int sum = maxDfs(depth + 1, totalScore + score, cards);
            if (sum > BUST_THRESHOLD.getSymbol()) {
                min = Math.min(min, sum);
                continue;
            }
            max = Math.max(max, sum);
        }
        if (max == Integer.MIN_VALUE) {
            return min;
        }
        return max;
    }

    public int calculateMinScore(List<Card> cards) {
        return minDfs(0, 0, cards);
    }

    private int minDfs(int depth, int totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }
        Card card = cards.get(depth);
        Set<Integer> scores = card.getRank().getScore();
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            min = Math.min(min, minDfs(depth + 1, totalScore + score, cards));
        }
        return min;
    }
}

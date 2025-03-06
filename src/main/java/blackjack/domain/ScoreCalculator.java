package blackjack.domain;

import static blackjack.domain.WinnerDecider.MAX_SCORE;

import java.util.List;

public class ScoreCalculator {

    public int calculateMaxScore(List<Card> cards) {
        return maxDfs(0, 0, cards);
    }

    private int maxDfs(int depth, int totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            int sum = maxDfs(depth + 1, totalScore + score, cards);
            if (sum > MAX_SCORE) {
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
        List<Integer> scores = card.getRank().getScore();
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            min = Math.min(min, minDfs(depth + 1, totalScore + score, cards));
        }
        return min;
    }
}

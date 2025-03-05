package blackjack.domain;

import java.util.List;

public class ScoreCalculator {

    public int calculateMaxScore(List<Card> cards) {
        return dfs(0, 0, cards);
    }

    private int dfs(int depth, int totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        List<Integer> scores = card.getRank().getScore();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int score : scores) {
            int sum = dfs(depth + 1, totalScore + score, cards);
            if (sum > 21) {
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
}

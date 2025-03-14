package blackjack.domain.card;

import java.util.List;
import java.util.Set;

public class ScoreCalculator {

    public Score calculateMaxScore(List<Card> cards) {
        return maxDfs(0, new Score(0), cards);
    }

    private Score maxDfs(int depth, Score totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }

        Card card = cards.get(depth);
        Set<Score> scores = card.getRank().getScore();
        Score max = Score.MIN;
        Score min = Score.MAX;
        for (Score score : scores) {
            Score sum = maxDfs(depth + 1, totalScore.plus(score), cards);
            if (sum.isBust()) {
                min = Score.min(min, sum);
                continue;
            }
            max = Score.max(max, sum);
        }
        if (max == Score.MIN) {
            return min;
        }
        return max;
    }

    public Score calculateMinScore(List<Card> cards) {
        return minDfs(0, new Score(0), cards);
    }

    private Score minDfs(int depth, Score totalScore, List<Card> cards) {
        if (depth == cards.size()) {
            return totalScore;
        }
        Card card = cards.get(depth);
        Set<Score> scores = card.getRank().getScore();
        Score min = Score.MAX;
        for (Score score : scores) {
            min = Score.min(min, minDfs(depth + 1, totalScore.plus(score), cards));
        }
        return min;
    }
}

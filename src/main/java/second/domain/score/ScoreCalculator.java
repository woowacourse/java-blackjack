package second.domain.score;

import second.domain.card.HandCards;
import second.domain.card.Score;

public class ScoreCalculator {
    private final ScoreCalculateStrategy scoreCalculateStrategy;

    public ScoreCalculator(ScoreCalculateStrategy scoreCalculateStrategy) {
        this.scoreCalculateStrategy = scoreCalculateStrategy;
    }

    public Score calculate(HandCards handCards) {
        return scoreCalculateStrategy.calculate(handCards);
    }
}

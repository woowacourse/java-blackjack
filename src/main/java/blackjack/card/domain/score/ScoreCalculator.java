package blackjack.card.domain.score;

import blackjack.card.domain.Card;

import java.util.Arrays;
import java.util.List;

public enum ScoreCalculator {

    ACE(new AceScoreStrategy()),
    DEFAULT(new DefaultScoreStrategy());

    private final ScoreStrategy scoreStrategy;

    ScoreCalculator(ScoreStrategy scoreStrategy) {
        this.scoreStrategy = scoreStrategy;
    }

    public static ScoreCalculator findByCards(List<Card> cards) {
        return Arrays.stream(values())
                .filter(scoreCalculator -> scoreCalculator.scoreStrategy.support(cards))
                .findFirst()
                .orElse(DEFAULT);
    }

    public int calculate(List<Card> cards) {
        return scoreStrategy.calculate(cards);
    }
}

package blackjack.domain.calculator;

import blackjack.domain.card.CardBundle;

import java.util.Arrays;

public enum ScoreCalculator {

    ACE(new AceScoreStrategy()),
    DEFAULT(new DefaultScoreStrategy());

    private final ScoreStrategy scoreStrategy;

    ScoreCalculator(ScoreStrategy scoreStrategy) {
        this.scoreStrategy = scoreStrategy;
    }

    public static ScoreCalculator findByCardBundle(CardBundle cardBundle) {
        return Arrays.stream(values())
                .filter(scoreCalculator -> scoreCalculator.support(cardBundle))
                .findFirst()
                .orElse(DEFAULT);
    }

    private boolean support(CardBundle cardBundle) {
        return this.scoreStrategy.support(cardBundle);
    }

    public int calculate(CardBundle cardBundle) {
        return scoreStrategy.calculate(cardBundle);
    }
}

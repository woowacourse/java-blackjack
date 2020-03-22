package blackjack.domain.score;

import blackjack.domain.calculator.ScoreCalculator;
import blackjack.domain.card.CardBundle;

public class Score {
    private static final int MAXIMUM_VALUE = 21;
    private static final int INIT_SIZE = 2;

    private final int score;
    private final int size;

    public Score(CardBundle cardBundle) {
        this.score = ScoreCalculator.findByCardBundle(cardBundle)
                .calculate(cardBundle);
        this.size = cardBundle.size();
    }

    public int getScore() {
        return score;
    }

    public boolean isBurst() {
        return score > MAXIMUM_VALUE;
    }

    public boolean isBlackjack() {
        return (size == INIT_SIZE) && (score == MAXIMUM_VALUE);
    }
}

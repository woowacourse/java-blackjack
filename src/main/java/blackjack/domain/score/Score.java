package blackjack.domain.score;

import blackjack.domain.calculator.ScoreCalculator;
import blackjack.domain.card.CardBundle;

public class Score {

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
        return score > 21;
    }

    public boolean isBlackjack() {
        return (size == 2) && (score == 21);
    }
}

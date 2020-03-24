package second.domain.score;

import second.domain.card.HandCards;

public class ScoreCalculator {
    public static final Score BLACK_JACK_MAX_SCORE = new Score(Score.BLACK_JACK_MAX_SCORE);
    private static final Score ACE_ADDITIONAL_SCORE = new Score(10);

    private ScoreCalculator() {
    }

    public static Score calculate(final HandCards handCards) {
        Score defaultSum = new Score(handCards.calculateDefaultSum());

        if(defaultSum.isBurst()) {
            return defaultSum;
        }

        if (handCards.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private static Score updateAceScore(final Score score) {
        Score bigAceScore = score.plus(ACE_ADDITIONAL_SCORE);

        if (bigAceScore.isBurst()) {
            return score;
        }

        return score.plus(ACE_ADDITIONAL_SCORE);
    }
}

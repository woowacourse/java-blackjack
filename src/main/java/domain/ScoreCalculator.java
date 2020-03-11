package domain;

import domain.card.HandCards;

public class ScoreCalculator {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACK_JACK_SCORE = 21;

    private ScoreCalculator() {
    }

    public static int calculate(HandCards handCards) {
        int defaultSum = handCards.calculateDefaultSum();

        if (handCards.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private static int updateAceScore(int score) {
        if (score + ACE_ADDITIONAL_SCORE > BLACK_JACK_SCORE) {
            return score;
        }
        return score + ACE_ADDITIONAL_SCORE;
    }
}

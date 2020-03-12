package domain;

import domain.card.possessable.HandCards;

public class BlackJackScoreManager {
    public static final int DEALER_DRAW_THRESHOLD = 16;
    private static final int ACE_ADDITIONAL_SCORE = 10;
    public static final int BLACK_JACK_SCORE = 21;

    private BlackJackScoreManager() {
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

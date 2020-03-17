package second.domain.score;

import second.domain.card.HandCards;
import second.domain.card.Score;


public class BlackJackScoreCalculateStrategy implements ScoreCalculateStrategy {
    private static final Score BLACK_JACK_SCORE = new Score(21);
    private static final Score DEALER_DRAW_THRESHOLD = new Score(16);
    private static final Score ACE_ADDITIONAL_SCORE = new Score(10);

    @Override
    public Score calculate(HandCards handCards) {
        Score defaultSum = new Score(handCards.calculateDefaultSum());

        if (handCards.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private static Score updateAceScore(Score score) {
        Score bigAceScore = score.plus(ACE_ADDITIONAL_SCORE);
        if (bigAceScore.isLargerThan(BLACK_JACK_SCORE)) {
            return score;
        }

        return score.plus(ACE_ADDITIONAL_SCORE);
    }
}

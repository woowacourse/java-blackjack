package domain.result;

import domain.gamer.Gamer;
import domain.gamer.Hand;
import domain.result.score.Score;
import domain.result.score.ScoreCalculable;

public class BlackJackRule implements ScoreCalculable {
    public static final Score BLACKJACK_SCORE = Score.of(21);
    public static final Score DEALER_THRESHOLD_SCORE = Score.of(16);
    private static final Score ACE_ADDITIONAL_SCORE = Score.of(10);

    @Override
    public Score calculateScore(Gamer gamer) {
        Hand hand = gamer.getHand();
        Score defaultSum = hand.calculateDefaultSum();

        if (hand.hasAce()) {
            return updateAceScore(defaultSum);
        }

        return defaultSum;
    }

    private Score updateAceScore(Score defaultSum) {
        Score aceAdditionalScore = defaultSum.plus(ACE_ADDITIONAL_SCORE);

        if (aceAdditionalScore.isBiggerThan(BLACKJACK_SCORE)) {
            return defaultSum;
        }

        return aceAdditionalScore;
    }
}

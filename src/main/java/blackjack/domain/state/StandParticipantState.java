package blackjack.domain.state;

import blackjack.domain.card.Score;

public class StandParticipantState extends ClosedParticipantState {

    StandParticipantState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitRate(Hand other) {
        Score score = getHand().sumScores();
        Score otherScore = other.sumScores();
        if (score.isBiggerThan(otherScore)) {
            return 1;
        }
        if (otherScore.isBiggerThan(score)) {
            return -1;
        }
        return 0;
    }
}

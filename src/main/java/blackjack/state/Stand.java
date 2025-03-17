package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.CardHand;

public class Stand extends Finished {

    public Stand(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(HandState otherHandState) {
        Score myScore = getScore();
        Score otherScore = otherHandState.getScore();

        if (otherHandState.isBust() || myScore.isHigherThan(otherScore)) {
            return GameResult.WIN;
        }
        if (otherHandState.isBlackjack() || otherScore.isHigherThan(myScore)) {
            return GameResult.LOSE;
        }
        return GameResult.DRAW;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public boolean isBust() {
        return false;
    }

}

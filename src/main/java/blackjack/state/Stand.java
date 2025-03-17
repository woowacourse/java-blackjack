package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Stand extends Finished {

    public Stand(CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public GameResult determineResult(State otherState) {
        Score myScore = getScore();
        Score otherScore = otherState.getScore();

        if (otherState.isBust() || myScore.isHigherThan(otherScore)) {
            return GameResult.WIN;
        }
        if (otherState.isBlackjack() || otherScore.isHigherThan(myScore)) {
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

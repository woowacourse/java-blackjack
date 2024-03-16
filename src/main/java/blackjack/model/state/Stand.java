package blackjack.model.state;

import blackjack.model.cards.Cards;
import blackjack.model.results.Result;

public class Stand extends Finished {
    public Stand(Cards cards) {
        super(cards);
    }

    @Override
    public Result determineResult(State otherState) {
        int myScore = getScore();
        int otherScore = otherState.getScore();
        if (otherState.isBust() || myScore > otherScore) {
            return Result.WIN;
        }
        if (otherState.isBlackJack() || myScore < otherScore) {
            return Result.LOSE;
        }
        return Result.PUSH;
    }
}

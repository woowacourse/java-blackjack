package blackjack.domain.participants.status.stopped;

import blackjack.domain.game.ResultType;
import blackjack.domain.game.Score;
import blackjack.domain.participants.Hand;
import blackjack.domain.participants.status.Status;

public class Stay extends Stopped {

    public Stay(final Hand cards) {
        super(cards);
    }

    @Override
    public ResultType findResultType(final Status opponent) {
        if (opponent.isBust()) {
            return ResultType.WIN;
        }
        if (opponent.isBlackjack()) {
            return ResultType.LOSE;
        }
        return findResultTypeWhenOpponentStay(opponent);
    }

    public ResultType findResultTypeWhenOpponentStay(final Status opponent) {
        final Score scoreOfOpponent = opponent.score();
        if (score().isBiggerThan(scoreOfOpponent)) {
            return ResultType.WIN;
        }
        if (scoreOfOpponent.isBiggerThan(score())) {
            return ResultType.LOSE;
        }
        return ResultType.TIE;
    }

}

package blackjack.domain.participant;

import blackjack.domain.result.Result;
import blackjack.domain.result.ResultEnum;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public ResultEnum checkResult(Participant participant) {
        if (this.isBust()) {
            return ResultEnum.LOSE;
        }
        if (participant.isBust()) {
            return ResultEnum.WIN;
        }
        return checkResultByScore(participant);
    }
}

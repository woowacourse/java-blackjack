package blackjack.domain.participant;

import blackjack.domain.result.Result;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
    }

    @Override
    public Result generateResult(Participant participant) {
        if (this.isBust()) {
            return Result.LOSE;
        }
        if (participant.isBust()) {
            return Result.WIN;
        }
        return generateResultByScore(participant);
    }
}

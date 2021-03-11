package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class LoseState extends NonBlackJackState {
    @Override
    public Result getResult() {
        return Result.LOSE;
    }
}

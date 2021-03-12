package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class LoseHandState extends NonBlackJackHandState {
    @Override
    public Result getResult() {
        return Result.LOSE;
    }
}

package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class WinState extends NonBlackJackState {
    @Override
    public Result getResult() {
        return Result.WIN;
    }
}

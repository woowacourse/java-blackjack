package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class WinHandState extends NonBlackJackHandState {
    @Override
    public Result getResult() {
        return Result.WIN;
    }
}

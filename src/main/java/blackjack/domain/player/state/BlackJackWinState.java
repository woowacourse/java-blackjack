package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class BlackJackWinState extends BlackJackState{
    @Override
    public Result getResult() {
        return Result.BLACKJACK;
    }
}

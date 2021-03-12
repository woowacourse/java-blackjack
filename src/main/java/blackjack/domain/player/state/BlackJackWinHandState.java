package blackjack.domain.player.state;

import blackjack.domain.result.Result;

public class BlackJackWinHandState extends BlackJackHandState {
    @Override
    public Result getResult() {
        return Result.BLACKJACK;
    }
}

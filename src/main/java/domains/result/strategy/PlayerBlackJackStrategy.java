package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public class PlayerBlackJackStrategy implements ResultStrategy {
    @Override
    public boolean checkResult(Player player) {
        return player.isBlackJack();
    }

    @Override
    public ResultType getResult(Player player, Dealer dealer) {
        return ResultType.BLACKJACK;
    }
}

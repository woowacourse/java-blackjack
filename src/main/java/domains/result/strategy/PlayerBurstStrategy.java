package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public class PlayerBurstStrategy implements ResultStrategy {
    @Override
    public boolean checkResult(Player player) {
        return player.isBurst();
    }

    @Override
    public ResultType getResult(Player player, Dealer dealer) {
        if (dealer.isBurst()) {
            return ResultType.DRAW;
        }
        return ResultType.LOSE;
    }
}

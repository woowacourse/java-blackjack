package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public class PlayerWinStrategy implements ResultStrategy {
    @Override
    public boolean checkResult(Player player, Dealer dealer) {
        return (dealer.isBurst() && !player.isBurst())
                || (player.score() > dealer.score());
    }

    @Override
    public ResultType getResult(Player player, Dealer dealer) {
        return ResultType.WIN;
    }
}

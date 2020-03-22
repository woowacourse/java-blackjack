package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public class PlayerScoreStrategy implements ResultStrategy {
    @Override
    public boolean checkResult(Player player) {
        return !player.isBurst() && !player.isBlackJack();
    }

    @Override
    public ResultType getResult(Player player, Dealer dealer) {
        if (player.score() > dealer.score()) {
            return ResultType.WIN;
        }
        if (player.score() < dealer.score()) {
            return ResultType.LOSE;
        }
        return ResultType.DRAW;
    }
}

package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public class PlayerBlackJackStrategy implements ResultStrategy {
    @Override
    public boolean checkResult(Player player, Dealer dealer) {
        // Player가 블랙잭일 경우, 게임이 종료되고 블랙잭의 결과를 얻게 됨.
        return player.isBlackJack();
    }

    @Override
    public ResultType getResult(Player player, Dealer dealer) {
        return ResultType.BLACKJACK;
    }
}

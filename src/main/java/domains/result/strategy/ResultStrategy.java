package domains.result.strategy;

import domains.result.ResultType;
import domains.user.Dealer;
import domains.user.Player;

public interface ResultStrategy {
    boolean checkResult(Player player, Dealer dealer);

    ResultType getResult(Player player, Dealer dealer);
}

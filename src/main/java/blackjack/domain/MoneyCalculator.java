package blackjack.domain;

import blackjack.domain.player.User;
import java.util.HashMap;
import java.util.Map;

public class MoneyCalculator {

    public Map<User, Integer> calculateAll(Map<User, Result> userResult, Map<User, BettingMoney> userBettingMoney) {
        Map<User, Integer> userRevenueResult = new HashMap<>();
        for (User user : userResult.keySet()) {
            userRevenueResult.put(user, calculate(user, userResult.get(user), userBettingMoney.get(user)));
        }
        return userRevenueResult;
    }

    public int calculate(User user, Result result, BettingMoney bettingMoney) {
        if (result == Result.WIN && user.isBlackJack()) {
            return bettingMoney.getValue() * 3 / 2;
        }
        if (result == Result.WIN) {
            return bettingMoney.getValue();
        }
        if (result == Result.LOSE) {
            return bettingMoney.getValue() * -1;
        }
        return 0;
    }
}

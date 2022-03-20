package blackjack.domain.money;

import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBettingMoney {

    private Map<User, BettingMoney> userBettingMoney;
    private Map<User, Integer> userRevenue;

    public UserBettingMoney(final List<User> users) {
        initUserBettingMoney(users);
    }

    private void initUserBettingMoney(final List<User> users) {
        userBettingMoney = new HashMap<>();
        users.forEach(user -> userBettingMoney.put(user, null));
    }

    public void putBettingMoney(final User user, final int money) {
        userBettingMoney.put(user, BettingMoney.of(money));
    }

    public Map<User, Integer> getUserRevenue(final Map<User, Result> userResult) {
        Map<User, Integer> userRevenue = new HashMap<>();
        for (User user : userBettingMoney.keySet()) {
            BettingMoney bettingMoney = userBettingMoney.get(user);
            Result result = userResult.get(user);
            int revenue = (int) bettingMoney.calculateRevenue(user, result);
            userRevenue.put(user, revenue);
        }
        return this.userRevenue = userRevenue;
    }

    public int getDealerRevenue() {
        int sum = 0;
        for (User user : userRevenue.keySet()) {
            sum += userRevenue.get(user);
        }
        return sum * -1;
    }
}

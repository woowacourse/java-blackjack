package blackjack.money;

import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserBettingMoney {

    private Map<User, BettingMoney> userBettingMoney;
    private Map<String, Integer> userRevenue;

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

    public Map<String, Integer> getUserRevenue(final Map<String, Result> userResult) {
        Map<String, Integer> userRevenue = new HashMap<>();
        for (User user : userBettingMoney.keySet()) {
            int revenue = (int) userBettingMoney.get(user).calculateRevenue(user, userResult.get(user.getName()));
            userRevenue.put(user.getName(), revenue);
        }
        return this.userRevenue = userRevenue;
    }

    public int getDealerRevenue() {
        int sum = 0;
        for (String userName : userRevenue.keySet()) {
            sum += userRevenue.get(userName);
        }
        return sum * -1;
    }

    public Map<User, BettingMoney> getUserBettingMoney() {
        return Collections.unmodifiableMap(userBettingMoney);
    }
}

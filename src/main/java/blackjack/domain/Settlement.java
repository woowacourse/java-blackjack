package blackjack.domain;

import blackjack.domain.game.Result;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Settlement {

    private final Map<Name, Stake> userPrize;
    private final Map<Name, Integer> profit;

    public Settlement(Map<Name, Stake> userPrize) {
        this.userPrize = Map.copyOf(userPrize);
        this.profit = initializeSetting();
    }

    private Map<Name, Integer> initializeSetting() {
        final LinkedHashMap<Name, Integer> profit = new LinkedHashMap<>();
        profit.put(new Name("딜러"), 0);
        return profit;
    }

    public int getProfit(final Name name, final Result result) {
        final Stake stake = userPrize.get(name);
        return result.getProfit(stake.getStake());
    }

    public void enrollResult(Map<Result, List<User>> resultData) {
        for (Result result : resultData.keySet()) {
            calculateProfit(resultData.get(result), result);
        }
    }

    private void calculateProfit(final List<User> users, final Result result) {
        for (User user : users) {
            final Name userName = user.getName();
            final int userProfit = getProfit(userName, result);
            profit.computeIfPresent(userName, (name, value) -> value + userProfit);
            profit.putIfAbsent(userName, userProfit);
        }
    }

    public int getDealerProfit() {
        final int profitSum = profit.values().stream()
                .mapToInt(value -> value.intValue())
                .sum();
        return (-1) * profitSum;
    }

    public Map<Name, Integer> getProfit() {
        return Collections.unmodifiableMap(profit);
    }
}

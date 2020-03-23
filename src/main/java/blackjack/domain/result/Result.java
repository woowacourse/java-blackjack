package blackjack.domain.result;

import blackjack.domain.user.User;

import java.util.Objects;

public class Result {
    private final User user;
    private final double profit;

    public Result(User user, double profit) {
        Objects.requireNonNull(user);
        this.user = user;
        this.profit = profit;
    }

    public User getUser() {
        return user;
    }

    public double getProfit() {
        return profit;
    }

    public String getUserName() {
        return user.getName().getName();
    }
}

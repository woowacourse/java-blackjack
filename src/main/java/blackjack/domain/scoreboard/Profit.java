package blackjack.domain.scoreboard;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.User;

import java.util.Arrays;
import java.util.function.BiPredicate;

import static blackjack.domain.user.Status.*;

public enum Profit {
    BLACKJACK_WIN(1.5
            , (user, dealer) -> (user.isSameStatus(BLACKJACK) && dealer.isNotStatus(BLACKJACK))
            , (user, dealer) -> false),
    WIN(1
            , (user, dealer) -> user.isSameStatus(STAY) && dealer.isSameStatus(BURST)
            , (userScore, dealerScore) -> userScore > dealerScore),
    DRAW(0
            , (user, dealer) -> user.isSameStatus(BLACKJACK) && dealer.isSameStatus(BLACKJACK)
            , Integer::equals),
    LOSE(-1
            , (user, dealer) -> (user.isSameStatus(STAY) && dealer.isSameStatus(BLACKJACK)) || user.isSameStatus(BURST)
            , (userScore, dealerScore) -> userScore < dealerScore);

    private static final String CANT_FIND_PROFIT_ERROR_MSG = "일치하는 승패조건이 없습니다.";

    private final double profit;
    private final BiPredicate<User, Dealer> findByStatus;
    private final BiPredicate<Integer, Integer> findByScore;

    Profit(double profit, BiPredicate<User, Dealer> findByStatus, BiPredicate<Integer, Integer> findByScore) {
        this.profit = profit;
        this.findByStatus = findByStatus;
        this.findByScore = findByScore;
    }

    public static Profit decideProfit(User user, Dealer dealer) {

        return Arrays.stream(values())
                .filter(profit -> profit.findByStatus.test(user, dealer))
                .findFirst()
                .orElseGet(() -> decideProfitByScore(user, dealer));
    }

    private static Profit decideProfitByScore(User user, Dealer dealer) {
        int userScore = user.calculateScore();
        int dealerScore = dealer.calculateScore();

        return Arrays.stream(values())
                .filter(profit -> profit.findByScore.test(userScore, dealerScore))
                .findAny().orElseThrow(() -> new IllegalArgumentException(CANT_FIND_PROFIT_ERROR_MSG));
    }

    public double getProfit() {
        return profit;
    }
}
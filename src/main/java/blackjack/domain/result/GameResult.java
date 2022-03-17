package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.money.BettingMoney;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResult {

    private static final int MINUS = -1;
    private static final double BLACKJACK_RATE = 1.5;

    private final Map<String, Result> userResult;
    private final Map<Result, Integer> dealerResult;

    private GameResult(final Map<String, Result> userResult, final Map<Result, Integer> dealerResult) {
        this.userResult = userResult;
        this.dealerResult = dealerResult;
    }

    public static GameResult createPlayerGameResult(final Dealer dealer, final Users users) {
        Map<String, Result> userResult = new HashMap<>();
        Map<Result, Integer> dealerResult = initializeDealerResultCount();
        for (final User user : users.getUsers()) {
            userResult.put(user.getName(), user.findResult(dealer));
            dealerResult.compute(dealer.findResult(user), (result, count) -> count + 1);
        }
        return new GameResult(userResult, dealerResult);
    }

    private static Map<Result, Integer> initializeDealerResultCount() {
        return new HashMap<>(Map.ofEntries(
                Map.entry(Result.WIN, 0),
                Map.entry(Result.DRAW, 0),
                Map.entry(Result.LOSE, 0)
        ));
    }

    public Map<String, Result> getUserResult() {
        return Collections.unmodifiableMap(userResult);
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<String, Integer> getUserRevenue(final Map<User, BettingMoney> userBettingMoney) {
        Map<String, Integer> userRevenue = new HashMap<>();
        for (User user : userBettingMoney.keySet()) {
            int revenue = (int) calculateRevenue(user, userResult.get(user.getName()), userBettingMoney.get(user));
            userRevenue.put(user.getName(), revenue);
        }
        return userRevenue;
    }

    private double calculateRevenue(User user, Result result, BettingMoney bettingMoney) {
        if (result == Result.WIN && user.isBlackJack()) {
            return bettingMoney.getValue() * BLACKJACK_RATE;
        }
        if (result == Result.WIN) {
            return bettingMoney.getValue();
        }
        if (result == Result.LOSE) {
            return bettingMoney.getValue() * MINUS;
        }
        return 0;
    }

    public int getDealerRevenue(Map<String, Integer> userRevenue) {
        int sum = 0;
        for (String userName : userRevenue.keySet()) {
            sum += userRevenue.get(userName);
        }
        return sum * MINUS;
    }
}

package util;

import domain.game.Result;
import domain.game.Results;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.User;
import domain.player.Users;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultGenerator {
    private ResultGenerator() {
    }

    public static Results create(final Dealer dealer, final Users users, BettingLogs bettingLogs) {
        Map<Name, Result> results = new LinkedHashMap<>();

        addDealerResults(dealer, users, results, bettingLogs);
        addUserResults(dealer, users, results, bettingLogs);
        return new Results(results);
    }

    private static void addDealerResults(final Dealer dealer, final Users users, final Map<Name, Result> results, BettingLogs bettingLogs) {
        Result result = new Result();

        for (User user : users) {
            BettingLog userBettingLog = bettingLogs.getUserLog(user.getName());

            result.calculateWinningMoney(dealer, user, userBettingLog);
        }
        results.put(new Name(dealer.getName()), result);
    }

    private static void addUserResults(final Dealer dealer, final Users users, final Map<Name, Result> results, BettingLogs bettingLogs) {
        for (User user : users) {
            BettingLog userBettingLog = bettingLogs.getUserLog(user.getName());
            Result result = new Result();

            result.calculateWinningMoney(user, dealer, userBettingLog);
            results.put(new Name(user.getName()), result);
        }
    }
}

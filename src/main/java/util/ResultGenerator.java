package util;

import domain.betting.BettingLog;
import domain.betting.BettingLogs;
import domain.game.Result;
import domain.game.Results;
import domain.player.Dealer;
import domain.player.User;
import domain.player.Users;

import java.util.ArrayList;
import java.util.List;

public class ResultGenerator {
    private ResultGenerator() {
    }

    public static Results create(final Dealer dealer, final Users users, BettingLogs bettingLogs) {
        List<Result> results = new ArrayList<>();

        addDealerResults(dealer, users, results, bettingLogs);
        addUserResults(dealer, users, results, bettingLogs);
        return new Results(results);
    }

    private static void addDealerResults(final Dealer dealer, final Users users, final List<Result> results, BettingLogs bettingLogs) {
        Result result = new Result(dealer.getName());

        for (User user : users) {
            BettingLog userBettingLog = bettingLogs.getUserLog(user.getName());

            result.calculateWinningMoney(dealer, user, userBettingLog);
        }
        results.add(result);
    }

    private static void addUserResults(final Dealer dealer, final Users users, final List<Result> results, BettingLogs bettingLogs) {
        for (User user : users) {
            BettingLog userBettingLog = bettingLogs.getUserLog(user.getName());
            Result result = new Result(user.getName());

            result.calculateWinningMoney(user, dealer, userBettingLog);
            results.add(result);
        }
    }
}

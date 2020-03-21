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

    public static Results create(final Dealer dealer, final Users users) {
        Map<Name, Result> results = new LinkedHashMap<>();

        addDealerResults(dealer, users, results);
        addUserResults(dealer, users, results);
        return new Results(results);
    }

    private static void addDealerResults(final Dealer dealer, final Users users, final Map<Name, Result> results) {
        Result result = new Result();

        for (User user : users) {
            result.calculateWinningMoney(dealer, user);
        }
        results.put(new Name(dealer.getName()), result);
    }

    private static void addUserResults(final Dealer dealer, final Users users, final Map<Name, Result> results) {
        for (User user : users) {
            Result result = new Result();

            result.calculateWinningMoney(user, dealer);
            results.put(new Name(user.getName()), result);
        }
    }
}

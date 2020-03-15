package util;

import domain.game.Result;
import domain.game.Results;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.User;
import domain.user.Users;

import java.util.ArrayList;
import java.util.List;

public class ResultGenerator {
    private ResultGenerator() {
    }

    public static Results create(final Dealer dealer, final Users users) {
        List<Result> results = new ArrayList<>();

        addDealerResults(dealer, users, results);
        addUserResults(dealer, users, results);
        return new Results(results);
    }

    private static void addDealerResults(final Dealer dealer, final Users users, final List<Result> results) {
        Result result = new Result(dealer.getName());

        for (User user : users) {
            calculateWinLose(dealer, user, result);
        }
        results.add(result);
    }

    private static void addUserResults(final Dealer dealer, final Users users, final List<Result> results) {
        for (User user : users) {
            Result result = new Result(user.getName());

            calculateWinLose(user, dealer, result);
            results.add(result);
        }
    }

    private static void calculateWinLose(final Player mainPlayer, final Player opponentPlayer, final Result result) {
        if (mainPlayer.isWin(opponentPlayer)) {
            result.addWinCount();
            return;
        }
        result.addLoseCount();
    }
}

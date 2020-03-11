package util;

import domain.game.Result;
import domain.game.Results;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class ResultGenerator {
    private ResultGenerator() {
    }

    public static Results create(Dealer dealer, Players players) {
        List<Result> results = new ArrayList<>();
        addDealerResults(dealer, players, results);
        addPlayerResults(dealer, players, results);
        return new Results(results);
    }

    private static void addDealerResults(Dealer dealer, Players players, List<Result> results) {
        Result result = new Result(dealer.getName());

        for (Player player : players) {
            calculateWinLose(dealer, player, result);
        }
        results.add(result);
    }

    private static void addPlayerResults(Dealer dealer, Players players, List<Result> results) {
        for (Player player : players) {
            Result result = new Result(player.getName());

            calculateWinLose(player, dealer, result);
            results.add(result);
        }
    }

    private static void calculateWinLose(User mainUser, User opponentUser, Result result) {
        if (mainUser.isWin(opponentUser)) {
            result.addWinCount();
            return;
        }
        result.addLoseCount();
    }
}

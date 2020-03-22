package blackjack.domain.result;

import blackjack.domain.user.*;
import blackjack.domain.user.component.Point;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Results {
    private List<Result> results;

    public Results(List<Result> results) {
        Objects.requireNonNull(results);
        if (results.isEmpty()) {
            throw  new IllegalArgumentException();
        }
        this.results = results;
    }

    public static Results createResults(Players players, Dealer dealer) {
        List<Result> results = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            ResultType resultType = computePlayerResultType(player, dealer);
            results.add(new Result(player, player.computeProfit(resultType)));
        }
        results.add(computeDealerResult(dealer, results));
        return new Results(results);
    }

    private static ResultType computePlayerResultType(User player, User dealer) {
        return ResultType.computeResult(player.getPoint(), dealer.getPoint());
    }

    private static Result computeDealerResult(User dealer, List<Result> results) {
        double sum = results.stream()
                .mapToDouble(x -> x.getProfit())
                .sum();
        return new Result(dealer, -1 * sum);
    }

    public List<Result> getPlayerResults() {
        return results.stream()
                .filter(x -> x.getUser().getClass().equals(Player.class))
                .collect(Collectors.toList());
    }

    public Result getDealerResult() {
         return results.stream()
                 .filter(x -> x.getUser().getClass().equals(Dealer.class))
                 .findAny()
                 .orElseThrow(IllegalAccessError::new);
    }
}

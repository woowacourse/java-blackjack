package blackjack.domain;

import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final Map<Result, Integer> dealerResult = new HashMap<>();

    {
        for (Result value : Result.values()) {
            dealerResult.put(value, 0);
        }
    }

    private Statistic() {
    }

    public static Statistic of() {
        return new Statistic();
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public void calculate(Table table) {
        for (Player player : table.getPlayers().get()) {
            player.calculateResult(table.getDealer().getPoint());
        }
        calculateDealerResult(table.getPlayers());
    }

    private void calculateDealerResult(Players players) {
        dealerResult.put(Result.LOSE, computeResultCount(players, Result.WIN));
        dealerResult.put(Result.DRAW, computeResultCount(players, Result.DRAW));
        dealerResult.put(Result.WIN, computeResultCount(players, Result.LOSE));
    }

    private int computeResultCount(Players players, Result result) {
        return (int) players.get().stream()
                .filter(player -> player.getResult().equals(result))
                .count();
    }
}

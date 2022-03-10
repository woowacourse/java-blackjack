package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final int dealerPoint;
    private final Map<Result, Integer> dealerResult = new HashMap<>();

    {
        for (Result value : Result.values()) {
            dealerResult.put(value, 0);
        }
    }

    private Statistic(Dealer dealer) {
        this.dealerPoint = dealer.getPoint();
    }

    public static Statistic of(Dealer dealer) {
        return new Statistic(dealer);
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public void calculate(Players players) {
        for (Player player : players.get()) {
            player.calculateResult(dealerPoint);
        }
        calculateDealerResult(players);
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

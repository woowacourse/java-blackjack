package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final int dealerPoint;
    private final Map<Result, Integer> dealerWinState = new HashMap<>();

    {
        for (Result value : Result.values()) {
            dealerWinState.put(value, 0);
        }
    }

    private Statistic(Dealer dealer) {
        this.dealerPoint = dealer.getPoint();
    }

    public static Statistic of(Dealer dealer) {
        return new Statistic(dealer);
    }

    public Map<Result, Integer> getDealerWinState() {
        return dealerWinState;
    }

    public void calculate(Players players) {
        for (Player player : players.get()) {
            player.calculateResult(dealerPoint);
        }
        calculateDealerWinState(players);
    }

    private void calculateDealerWinState(Players players) {
        dealerWinState.put(Result.LOSE, computeResultCount(players, Result.WIN));
        dealerWinState.put(Result.DRAW, computeResultCount(players, Result.DRAW));
        dealerWinState.put(Result.WIN, computeResultCount(players, Result.LOSE));
    }

    private int computeResultCount(Players players, Result result) {
        return (int) players.get().stream()
                .filter(player -> player.getResult().equals(result))
                .count();
    }
}

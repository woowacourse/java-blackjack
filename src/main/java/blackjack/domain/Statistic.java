package blackjack.domain;

import blackjack.domain.human.Dealer;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final int dealerPoint;
    private final Map<GameResult, Integer> dealerWinState = new HashMap<>();

    {
        for (GameResult value : GameResult.values()) {
            dealerWinState.put(value, 0);
        }
    }

    private Statistic(Dealer dealer) {
        this.dealerPoint = dealer.getPoint();
    }

    public static Statistic of(Dealer dealer) {
        return new Statistic(dealer);
    }

    public Map<GameResult, Integer> getDealerWinState() {
        return dealerWinState;
    }

    public void calculate(Players players) {
        for (Player player : players.getPlayers()) {
            player.calculateResult(dealerPoint);
        }
        calculateDealerWinState(players);
    }

    private void calculateDealerWinState(Players players) {
        dealerWinState.put(GameResult.LOSE,computeResultCount(players,GameResult.WIN));
        dealerWinState.put(GameResult.DRAW,computeResultCount(players,GameResult.DRAW));
        dealerWinState.put(GameResult.WIN,computeResultCount(players,GameResult.LOSE));
    }

    private int computeResultCount(Players players, GameResult result) {
        return (int) players.getPlayers().stream()
                        .filter(player -> player.getResult().equals(result))
                        .count();
    }
}

package blackjack.domain;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final Map<Result, Integer> dealerResult = new HashMap<>();
    private final Map<Player, Result> playerResult = new HashMap<>();

    private Statistic() {
        for (Result value : Result.values()) {
            dealerResult.put(value, 0);
        }
    }

    public static Statistic of() {
        return new Statistic();
    }

    public void calculate(Table table) {
        for (Player player : table.getPlayers().get()) {
            playerResult.put(player, calculateResult(player.getPoint(), table.getDealer().getPoint()));
        }
        calculateDealerResult(table.getPlayers());
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<Player, Result> getPlayersResult() {
        return playerResult;
    }

    private void calculateDealerResult(Players players) {
        dealerResult.put(Result.LOSE, computeResultCount(players, Result.WIN));
        dealerResult.put(Result.DRAW, computeResultCount(players, Result.DRAW));
        dealerResult.put(Result.WIN, computeResultCount(players, Result.LOSE));
    }

    private int computeResultCount(Players players, Result result) {
        return (int) players.get().stream()
                .filter(player -> playerResult.get(player).equals(result))
                .count();
    }

    private Result calculateResult(final int point, final int dealerPoint) {
        if (dealerPoint > BLACKJACK_NUMBER) {
            return getResultIfDealerBurst(point);
        }
        return getResultIfDealerNotBurst(dealerPoint, point);
    }

    private Result getResultIfDealerBurst(final int point) {
        if (point <= BLACKJACK_NUMBER) {
            return Result.WIN;
        }
        return Result.LOSE;
    }

    private Result getResultIfDealerNotBurst(final int dealerPoint, final int point) {
        if (point > BLACKJACK_NUMBER || dealerPoint > point) {
            return Result.LOSE;
        }
        if (dealerPoint == point) {
            return Result.DRAW;
        }
        return Result.WIN;
    }
}
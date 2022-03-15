package blackjack.domain;

import static blackjack.util.Constants.BLACKJACK_NUMBER;

import blackjack.domain.human.Player;
import blackjack.domain.human.Players;
import java.util.HashMap;
import java.util.Map;

public class Statistic {
    private final Map<Result, Integer> dealerResult = new HashMap<>();
    private final Map<Player, Result> playerResult = new HashMap<>();
    
    private Statistic(Table table) {
        for (Result value : Result.values()) {
            dealerResult.put(value, 0);
        }
        calculate(table);
    }
    
    public static Statistic of(Table table) {
        return new Statistic(table);
    }
    
    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }
    
    public Map<Player, Result> getPlayersResult() {
        return playerResult;
    }
    
    private void calculate(Table table) {
        calculateEachPlayers(table.getPlayers(), table.getDealer().getPoint());
        calculateDealerResult(table.getPlayers());
    }
    
    private void calculateEachPlayers(final Players players, final int dealerPoint) {
        for (Player player : players.get()) {
            playerResult.put(player, calculatePlayerResult(player.getPoint(), dealerPoint));
        }
    }
    
    private void calculateDealerResult(final Players players) {
        dealerResult.put(Result.LOSE, computeResultCount(players, Result.WIN));
        dealerResult.put(Result.DRAW, computeResultCount(players, Result.DRAW));
        dealerResult.put(Result.WIN, computeResultCount(players, Result.LOSE));
    }
    
    private Result calculatePlayerResult(final int point, final int dealerPoint) {
        if (dealerPoint > BLACKJACK_NUMBER) {
            return getResultIfDealerBurst(point);
        }
        return getResultIfDealerNotBurst(dealerPoint, point);
    }
    
    private int computeResultCount(final Players players, final Result result) {
        return (int) players.get().stream()
                .filter(player -> playerResult.get(player).equals(result))
                .count();
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

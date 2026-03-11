package domain.game;

import domain.participant.Player;

import java.util.*;

public class Result {
    private final Map<Player, ResultInfo> playersResult;
    private final int dealerResult;

    public Result(Map<Player, ResultInfo> playersResult) {
        this.playersResult = playersResult;
        this.dealerResult = calculateDealerResult(playersResult);
    }

    public Map<Player, ResultInfo> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public int getDealerResult() {
        return dealerResult;
    }

    private int calculateDealerResult(Map<Player, ResultInfo> playersResult) {
        int dealerResult = 0;
        for (Player player : playersResult.keySet()) {
            ResultInfo playerOutcome = playersResult.get(player);
            int playerBettingMoney = player.getBettingMoney();
            double playerYield = playerOutcome.getYield();
            dealerResult += (int) (playerBettingMoney * playerYield);
        }
        return -dealerResult;
    }

    public Map<String, Integer> calculatePlayerYield(Map<Player, ResultInfo> playersResult) {
        Map<String, Integer> playerYield = new HashMap<>();
        for (Player player : playersResult.keySet()) {
            playerYield.put(player.getName(),
                    (int) ((double) player.getBettingMoney() * playersResult.get(player).getYield()));
        }
        return playerYield;
    }
}

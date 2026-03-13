package domain.game;

import domain.participant.Player;

import java.util.*;

public class Result {
    private final Map<Player, GameResult> playersResult;
    private final int dealerResult;

    public Result(Map<Player, GameResult> playersResult) {
        this.playersResult = playersResult;
        this.dealerResult = calculateDealerResult(playersResult);
    }

    public Map<Player, GameResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public int getDealerResult() {
        return dealerResult;
    }

    private int calculateDealerResult(Map<Player, GameResult> playersResult) {
        int dealerResult = 0;
        for (Player player : playersResult.keySet()) {
            GameResult playerOutcome = playersResult.get(player);
            int playerBettingMoney = player.getBettingMoney();
            double playerYield = playerOutcome.getYield();
            dealerResult += (int) (playerBettingMoney * playerYield);
        }
        return -dealerResult;
    }

    public Map<String, Integer> calculatePlayerYield(Map<Player, GameResult> playersResult) {
        Map<String, Integer> playerYield = new HashMap<>();
        for (Player player : playersResult.keySet()) {
            playerYield.put(player.getName(),
                    (int) ((double) player.getBettingMoney() * playersResult.get(player).getYield()));
        }
        return playerYield;
    }
}

package domain.game;

import domain.participant.Player;

import java.math.BigDecimal;
import java.util.*;

public class Result {
    private static final int NEGATION_FACTOR = -1;
    private final Map<Player, GameResult> playersResult;
    private final BigDecimal dealerResult;

    public Result(Map<Player, GameResult> playersResult) {
        this.playersResult = playersResult;
        this.dealerResult = calculateDealerResult(playersResult);
    }

    public Map<Player, GameResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public BigDecimal getDealerResult() {
        return dealerResult;
    }

    private BigDecimal calculateDealerResult(Map<Player, GameResult> playersResult) {
        BigDecimal dealerResult = BigDecimal.ZERO;
        for (Player player : playersResult.keySet()) {
            GameResult playerOutcome = playersResult.get(player);
            BigDecimal playerBettingMoney = player.getBettingMoney();
            BigDecimal playerYield = playerOutcome.getYield();
            dealerResult = dealerResult.add(playerBettingMoney.multiply(playerYield));
        }
        return dealerResult.multiply(BigDecimal.valueOf(NEGATION_FACTOR));
    }

    public Map<String, BigDecimal> calculatePlayerYield() {
        Map<String, BigDecimal> playerYield = new HashMap<>();
        for (Player player : playersResult.keySet()) {
            playerYield.put(player.getName(),
                    player.getBettingMoney().multiply(playersResult.get(player).getYield()));
        }
        return playerYield;
    }
}

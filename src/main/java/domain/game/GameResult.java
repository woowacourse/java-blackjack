package domain.game;

import domain.participant.player.Player;

import java.math.BigDecimal;
import java.util.*;

public class GameResult {
    private static final int NEGATION_FACTOR = -1;
    private final Map<Player, Outcome> playersResult;
    private final BigDecimal dealerResult;

    public GameResult(Map<Player, Outcome> playersResult) {
        this.playersResult = playersResult;
        this.dealerResult = calculateDealerResult(playersResult);
    }

    public Map<Player, Outcome> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public BigDecimal getDealerResult() {
        return dealerResult;
    }

    private BigDecimal calculateDealerResult(Map<Player, Outcome> playersResult) {
        BigDecimal dealerResult = BigDecimal.ZERO;
        for (Player player : playersResult.keySet()) {
            Outcome playerOutcome = playersResult.get(player);
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

package domain.game;

import domain.participant.Player;

import java.util.Map;

public class EarningResult {
    private final Map<Player, Double> earningResult;

    public EarningResult(Map<Player, Double> earningResult) {
        this.earningResult = earningResult;
    }

    public double calcualteDealerEarning() {
        return -1 * earningResult.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}

package domain.result;

import domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameProfit {
    private final Map<String, Long> profitMoney = new LinkedHashMap<>();

    public GameProfit(Map<Player, GameResult> gameResultMap) {
        initializeMap(gameResultMap);
    }

    private void initializeMap(Map<Player, GameResult> gameResultMap) {
        for (Map.Entry<Player, GameResult> gameResultEntry : gameResultMap.entrySet()) {
            addProfitMoney(gameResultEntry.getKey(), gameResultEntry.getValue());
        }
    }

    private void addProfitMoney(Player player, GameResult gameResult) {
        long battingResult = player.calculateBattingProfit(gameResult.getProfit());
        profitMoney.put(player.getName(), battingResult);
    }

    public Map<String, Long> getPlayerProfit() {
        return Collections.unmodifiableMap(profitMoney);
    }

    public long getDealerProfit() {
        return -profitMoney.values().stream()
                .mapToLong(Long::longValue)
                .sum();
    }


}

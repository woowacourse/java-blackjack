package domain.result;

import domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProfitResult {
    private final Map<String, Long> playerProfitMap = new LinkedHashMap<>();

    private ProfitResult(BlackjackResult blackjackResult) {
        calculateProfits(blackjackResult);
    }

    public static ProfitResult from(BlackjackResult blackjackResult) {
        return new ProfitResult(blackjackResult);
    }

    private void calculateProfits(BlackjackResult blackjackResult) {
        for (Map.Entry<Player, MatchCase> entry : blackjackResult.getPlayerResultMap().entrySet()) {
            Player player = entry.getKey();
            MatchCase matchCase = entry.getValue();
            playerProfitMap.put(player.getName(), player.calculateProfit(matchCase));
        }
    }

    public Map<String, Long> getPlayerProfitMap() {
        return Map.copyOf(playerProfitMap);
    }

    public long getDealerBenefit() {
        return (-1) * playerProfitMap.values().stream().reduce(0L, Long::sum);
    }
}

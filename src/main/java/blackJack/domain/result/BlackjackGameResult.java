package blackJack.domain.result;

import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGameResult {

    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    private BlackjackGameResult(int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = new LinkedHashMap<>(playerEarnings);
    }

    public static BlackjackGameResult from(Map<Player, OutCome> outComes) {
        return calculateEarning(outComes);
    }

    private static BlackjackGameResult calculateEarning(Map<Player, OutCome> outComes) {
        final Map<String, Integer> playerEarnings = new LinkedHashMap<>();
        outComes.forEach((key, value) ->
                playerEarnings.put(key.getName(), value.calculateEarning(key.getBet())));

        int dealerEarning = playerEarnings.values().stream()
                .mapToInt(earning -> earning * -1)
                .sum();

        return new BlackjackGameResult(dealerEarning, playerEarnings);
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return playerEarnings;
    }
}

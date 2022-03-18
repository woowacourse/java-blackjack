package blackJack.domain.result;

import blackJack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackJackGameResult {

    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    private BlackJackGameResult(int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = new LinkedHashMap<>(playerEarnings);
    }

    public static BlackJackGameResult from(Map<Player, OutCome> outComes) {
        return calculateEarning(outComes);
    }

    private static BlackJackGameResult calculateEarning(Map<Player, OutCome> outComes) {
        final Map<String, Integer> playerEarnings = new LinkedHashMap<>();
        outComes.forEach((key, value) ->
                playerEarnings.put(key.getName(), value.calculateEarning(key.getBet())));

        int dealerEarning = playerEarnings.values().stream()
                .mapToInt(earning -> earning * -1)
                .sum();

        return new BlackJackGameResult(dealerEarning, playerEarnings);
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return playerEarnings;
    }
}

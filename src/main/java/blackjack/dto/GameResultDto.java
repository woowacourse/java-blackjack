package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultDto {

    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    public GameResultDto(int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return new LinkedHashMap<>(playerEarnings);
    }
}

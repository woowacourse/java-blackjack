package blackjack.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResultDto {

    private final int dealerDrawCount;
    private final int dealerEarning;
    private final Map<String, Integer> playerEarnings;

    public GameResultDto(int dealerDrawCount, int dealerEarning, Map<String, Integer> playerEarnings) {
        this.dealerDrawCount = dealerDrawCount;
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    public int getDealerDrawCount() {
        return dealerDrawCount;
    }

    public int getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Integer> getPlayerEarnings() {
        return new LinkedHashMap<>(playerEarnings);
    }
}

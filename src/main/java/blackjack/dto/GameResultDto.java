package blackjack.dto;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.result.BlackJackResult;

public class GameResultDto {

    private final double dealerEarning;
    private final Map<String, Double> playerEarnings;

    public GameResultDto(double dealerEarning, Map<String, Double> playerEarnings) {
        this.dealerEarning = dealerEarning;
        this.playerEarnings = playerEarnings;
    }

    public double getDealerEarning() {
        return dealerEarning;
    }

    public Map<String, Double> getPlayerEarnings() {
        return new HashMap<>(playerEarnings);
    }

    public Map<BlackJackResult, Integer> getDealerResult() {
        return null;
    }

    public Map<String, BlackJackResult> getPlayerResults() {
        return null;
    }
}

package blackjack.domain.dto;

import java.util.Map;

public class GameResultDto {
    private final Map<String, Integer> userAndProfit;

    public GameResultDto(Map<String, Integer> userAndProfit) {
        this.userAndProfit = userAndProfit;
    }

    public Map<String, Integer> getUserAndProfit() {
        return userAndProfit;
    }
}

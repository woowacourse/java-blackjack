package dto;

import domain.blackjack.GameResult;
import java.util.Map;

public class DealerGameResultDTO {
    private final Map<GameResult, Integer> dealerGameResultCounts;

    public DealerGameResultDTO(Map<GameResult, Integer> dealerGameResultCounts) {
        this.dealerGameResultCounts = dealerGameResultCounts;
    }

    public Map<GameResult, Integer> getDealerGameResultCounts() {
        return dealerGameResultCounts;
    }
}

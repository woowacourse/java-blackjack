package dto;

import domain.GameResult;
import java.util.Map;

public record BlackjackResult(
        int dealerProfit,
        Map<String, Integer> gamblerProfits
) {
    public static BlackjackResult from(GameResult gameResult) {

        return new BlackjackResult(gameResult.dealerProfit(), gameResult.gamblerProfits());
    }

}
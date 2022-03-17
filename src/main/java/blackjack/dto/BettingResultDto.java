package blackjack.dto;

import blackjack.domain.result.BettingResult;

import java.util.Map;

public class BettingResultDto {

    private final Map<String, Double> playerResult;
    private final double dealerResult;

    private BettingResultDto(Map<String, Double> playerResult, double dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public static BettingResultDto toDto(BettingResult result) {
        return new BettingResultDto(result.getPlayerResult(), result.getDealerResult());
    }

    public Map<String, Double> getPlayerResult() {
        return playerResult;
    }

    public double getDealerResult() {
        return dealerResult;
    }
}

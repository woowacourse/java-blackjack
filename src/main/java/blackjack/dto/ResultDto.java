package blackjack.dto;

import java.util.Map;

public class ResultDto {

    private final double dealerResult;
    private final Map<String, Double> playerResult;

    public ResultDto(double dealerResult, Map<String, Double> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public double getDealerResult() {
        return dealerResult;
    }

    public Map<String, Double> getPlayerResult() {
        return playerResult;
    }
}

package domain.result.dto;

import domain.result.BettingResult;

public record BettingProfitDto(
        String playerName,
        long totalProfit
) {
    public static BettingProfitDto from(BettingResult bettingResult) {
        return new BettingProfitDto(bettingResult.getParticipantName().name(), Math.round(bettingResult.getProfit()));
    }
}

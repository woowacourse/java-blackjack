package dto;

import java.util.List;

public record BettingResultDto(List<PlayerProfitDto> playerProfitDtos, long dealerProfit) {
}

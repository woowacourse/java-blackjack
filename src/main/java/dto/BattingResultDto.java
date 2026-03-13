package dto;

import java.util.List;

public record BattingResultDto(List<PlayerProfitDto> playerProfitDtos, long dealerProfit) {
}

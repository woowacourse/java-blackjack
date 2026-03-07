package dto;

import java.util.List;

public record ResultDto(
        DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtos,
        int dealerWinCount,
        int dealerLossCount
) {
    public static ResultDto of(DealerResultDto dealerResultDto, List<PlayerResultDto> playerResultDtos) {
        int dealerWinCount = 0;
        int dealerLossCount = 0;
        for (PlayerResultDto playerResultDto : playerResultDtos) {
            if (playerResultDto.isWin().equals("승")) {
                dealerLossCount++;
            }

            if (playerResultDto.isWin().equals("패")) {
                dealerWinCount++;
            }
        }
        return new ResultDto(dealerResultDto, playerResultDtos, dealerWinCount, dealerLossCount);
    }
}

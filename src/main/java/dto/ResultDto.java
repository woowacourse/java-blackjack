package dto;

import java.util.List;

public record ResultDto(
        DealerResultDto dealerResultDto,
        List<PlayerResultDto> playerResultDtos,
        int dealerFinalMoney
) {
    public static ResultDto of(DealerResultDto dealerResultDto, List<PlayerResultDto> playerResultDtos) {
        int dealerFinalMoney = 0;
        for (PlayerResultDto playerResultDto : playerResultDtos) {
            dealerFinalMoney -= playerResultDto.finalMoney();
        }
        return new ResultDto(dealerResultDto, playerResultDtos, dealerFinalMoney);
    }
}

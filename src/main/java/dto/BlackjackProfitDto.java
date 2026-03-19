package dto;

import java.util.List;

public record BlackjackProfitDto(
    int dealerProfit,
    List<PlayerResultDto> playerResultDtoList
) {

}

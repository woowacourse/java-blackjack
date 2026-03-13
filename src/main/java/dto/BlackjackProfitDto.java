package dto;

import java.util.List;

public record BlackjackProfitDto(
    double dealerProfit,
    List<PlayerResultDto> playerResultDtoList
) {

}

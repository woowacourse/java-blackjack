package dto;

import domain.result.Results;
import java.util.List;

public record FinalResultDto(long dealerProfit, List<PlayerResultDto> playerResultDtos) {

    public static FinalResultDto from(Results results) {
        List<PlayerResultDto> playerResultDtos = PlayerResultDto.fromResults(results);

        long dealerProfit = playerResultDtos.stream()
                .mapToLong(PlayerResultDto::profit)
                .sum() * -1;

        return new FinalResultDto(dealerProfit, playerResultDtos);
    }
}

package blackjack.domain;

import blackjack.dto.BettingResultDto;
import java.util.List;
import java.util.stream.Collectors;

public record BettingResultDtos(List<BettingResultDto> bettingResultDtos) {
    public static BettingResultDtos of(final BettingResults bettingResults, final BettingResult dealerResult) {
        List<BettingResultDto> bettingResultDtos = bettingResults.getBettingResults().stream().
                map(BettingResultDto::from)
                .collect(Collectors.toList());
        bettingResultDtos.add(BettingResultDto.from(dealerResult));

        return new BettingResultDtos(bettingResultDtos);
    }
}

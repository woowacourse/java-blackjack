package blackjack.dto;

import blackjack.domain.betting.DealerBetting;
import blackjack.domain.betting.PlayerBettings;
import java.util.List;
import java.util.stream.Collectors;

public record BettingResultDtos(List<BettingResultDto> bettingResultDtos) {
    public static BettingResultDtos of(final PlayerBettings bettingResults, final DealerBetting dealerBetting) {
        List<BettingResultDto> bettingResultDtos = bettingResults.getPlayerBettings().stream()
                .map(BettingResultDto::from)
                .collect(Collectors.toList());

        bettingResultDtos.add(BettingResultDto.from(dealerBetting));

        return new BettingResultDtos(bettingResultDtos);
    }
}

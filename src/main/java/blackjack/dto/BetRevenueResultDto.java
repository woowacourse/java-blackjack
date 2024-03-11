package blackjack.dto;

import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Map;

public record BetRevenueResultDto(
        List<PlayerBetResultDto> playersBetResult,
        DealerBetResultDto dealerBetResults
) {

    public static BetRevenueResultDto of(
            final Map<PlayerName, BetRevenue> playersBetResult,
            final BetRevenue dealerBetRevenue,
            final String dealerName
    ) {
        return new BetRevenueResultDto(convertToPlayersDto(playersBetResult), DealerBetResultDto.of(dealerName, dealerBetRevenue));
    }

    private static List<PlayerBetResultDto> convertToPlayersDto(
            final Map<PlayerName, BetRevenue> playersBetResult) {
        return playersBetResult.entrySet().stream()
                .map(entry -> PlayerBetResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}

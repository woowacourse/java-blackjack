package blackjack.dto;

import blackjack.domain.player.bet.BetRevenue;
import blackjack.domain.player.PlayerName;
import java.util.List;
import java.util.Map;

public record BetRevenueResultDto(
        List<PlayerBetResultDto> playersBetResult,
        BetRevenue dealerRevenue
) {

    public static BetRevenueResultDto of(
            final Map<PlayerName, BetRevenue> playersBetResult,
            final BetRevenue dealerBetRevenue
    ) {
        return new BetRevenueResultDto(convertToPlayersDto(playersBetResult), dealerBetRevenue);
    }

    private static List<PlayerBetResultDto> convertToPlayersDto(final Map<PlayerName, BetRevenue> playersBetResult) {
        return playersBetResult.entrySet().stream()
                .map(entry -> new PlayerBetResultDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}

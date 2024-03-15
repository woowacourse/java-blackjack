package blackjack.dto;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.player.UserName;
import java.util.List;
import java.util.Map;

public record BetRevenueResultDto(
        List<PlayerBetResultDto> playersBetResult,
        BetRevenue dealerRevenue
) {

    public static BetRevenueResultDto of(
            final Map<UserName, BetRevenue> playersBetResult,
            final BetRevenue dealerBetRevenue
    ) {
        return new BetRevenueResultDto(convertToPlayersDto(playersBetResult), dealerBetRevenue);
    }

    private static List<PlayerBetResultDto> convertToPlayersDto(final Map<UserName, BetRevenue> playersBetResult) {
        return playersBetResult.entrySet().stream()
                .map(entry -> new PlayerBetResultDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}

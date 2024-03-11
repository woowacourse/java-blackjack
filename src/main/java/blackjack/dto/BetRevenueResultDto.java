package blackjack.dto;

import blackjack.domain.bet.BetRevenue;
import blackjack.domain.participant.ParticipantName;
import java.util.List;
import java.util.Map;

public record BetRevenueResultDto(
        List<PlayerBetResultDto> playersBetResult,
        DealerBetResultDto dealerBetResults
) {

    public static BetRevenueResultDto of(
            final Map<ParticipantName, BetRevenue> playersBetResult,
            final BetRevenue dealerBetRevenue,
            final String dealerName
    ) {
        return new BetRevenueResultDto(convertToPlayersDto(playersBetResult), DealerBetResultDto.of(dealerName, dealerBetRevenue));
    }

    private static List<PlayerBetResultDto> convertToPlayersDto(
            final Map<ParticipantName, BetRevenue> playersBetResult) {
        return playersBetResult.entrySet().stream()
                .map(entry -> PlayerBetResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}

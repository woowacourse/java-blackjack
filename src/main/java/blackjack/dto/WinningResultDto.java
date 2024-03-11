package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.betting.BettingStatus;
import java.util.List;
import java.util.Map;

public record WinningResultDto(
        List<PlayerWinningResultDto> playersWinningResult,
        List<DealerWinningResultDto> dealerWinningResults,
        String dealerName
) {

    public static WinningResultDto of(
            final Map<ParticipantName, BettingStatus> playersWinningResult,
            final Map<BettingStatus, Long> dealerWinningResults,
            final ParticipantName dealerName
    ) {
        return new WinningResultDto(convertToPlayersDto(playersWinningResult), convertToDealerDto(dealerWinningResults),
                dealerName.getName());
    }

    private static List<PlayerWinningResultDto> convertToPlayersDto(
            final Map<ParticipantName, BettingStatus> playersWinningResult) {
        return playersWinningResult.entrySet().stream()
                .map(entry -> PlayerWinningResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static List<DealerWinningResultDto> convertToDealerDto(final Map<BettingStatus, Long> dealerWinningResults) {
        return dealerWinningResults.entrySet().stream()
                .map(entry -> DealerWinningResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}

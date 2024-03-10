package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import java.util.List;
import java.util.Map;

public record WinningResultDto(
        List<PlayerWinningResultDto> playersWinningResult,
        List<DealerWinningResultDto> dealerWinningResults,
        String dealerName
) {

    public static WinningResultDto of(
            final Map<ParticipantName, WinStatus> playersWinningResult,
            final Map<WinStatus, Long> dealerWinningResults,
            final ParticipantName dealerName
    ) {
        return new WinningResultDto(convertToPlayersDto(playersWinningResult), convertToDealerDto(dealerWinningResults),
                dealerName.getName());
    }

    private static List<PlayerWinningResultDto> convertToPlayersDto(
            final Map<ParticipantName, WinStatus> playersWinningResult) {
        return playersWinningResult.entrySet().stream()
                .map(entry -> PlayerWinningResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private static List<DealerWinningResultDto> convertToDealerDto(final Map<WinStatus, Long> dealerWinningResults) {
        return dealerWinningResults.entrySet().stream()
                .map(entry -> DealerWinningResultDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }
}

package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import java.util.LinkedHashMap;
import java.util.Map;

public record WinningResultDTO(Map<String, String> playerWinningResult, Map<String, Long> dealerWinningResult) {
    public static WinningResultDTO of(final Map<ParticipantName, WinStatus> playerWinningResult,
                                      final Map<WinStatus, Long> dealerWinningResult) {
        return new WinningResultDTO(convertToPlayersDTO(playerWinningResult), convertToDealerDTO(dealerWinningResult));
    }

    private static Map<String, String> convertToPlayersDTO(
            final Map<ParticipantName, WinStatus> rawPlayerWinningResult) {
        final Map<String, String> playerWinningResults = new LinkedHashMap<>();
        rawPlayerWinningResult.forEach((key, value) -> playerWinningResults.put(key.getName(), value.name()));

        return playerWinningResults;
    }

    private static Map<String, Long> convertToDealerDTO(final Map<WinStatus, Long> rawDealerWinningResult) {
        final Map<String, Long> dealerWinningResults = new LinkedHashMap<>();
        rawDealerWinningResult.forEach((key, value) -> dealerWinningResults.put(key.name(), value));

        return dealerWinningResults;
    }
}

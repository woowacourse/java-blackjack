package blackjack.dto;

import blackjack.domain.participant.ParticipantName;
import blackjack.domain.result.WinStatus;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public record WinningResultDto(List<PlayerWinningResultDto> playersWinningResult,
                               List<WinningCountDto> dealerWinningResult) {
    public static WinningResultDto of(final Map<ParticipantName, WinStatus> playerWinningResults,
                                      final Map<WinStatus, Long> dealerWinningResult) {
        return new WinningResultDto(
                convertToDto(playerWinningResults, PlayerWinningResultDto::of),
                convertToDto(dealerWinningResult, WinningCountDto::of));
    }

    private static <K, V, D> List<D> convertToDto(final Map<K, V> target, BiFunction<K, V, D> function) {
        return target.entrySet().stream()
                .map(entry -> function.apply(entry.getKey(), entry.getValue()))
                .toList();
    }
}

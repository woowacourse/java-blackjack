package blackjack.dto;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.WinStatus;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record WinningResultDTO(Map<String, String> playerWinningResult, Map<String, Integer> dealerWinningResult) {

    public static WinningResultDTO of(final Map<String, String> winningResults,
                                      final Map<WinStatus, Integer> dealerResult) {
        return new WinningResultDTO(winningResults, covertToDTO(dealerResult));
    }

    private static Map<String, Integer> covertToDTO(final Map<WinStatus, Integer> dealerResult) {
        return dealerResult.entrySet().stream()
                .collect(toMap(entry -> entry.getKey().name(),
                        Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }
}

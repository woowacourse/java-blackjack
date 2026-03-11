package blackjack.dto;

import java.util.Map;

public record WinningResult(
        Map<String, Integer> winningResult
) {

    public static WinningResult from(Map<String, Integer> winningResult) {
        return new WinningResult(winningResult);
    }

    public int getProfitOfDealer() {
        return -winningResult.values().stream()
                .mapToInt(integer -> integer)
                .sum();
    }

}

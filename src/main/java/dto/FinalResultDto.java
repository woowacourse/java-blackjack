package dto;

import domain.WinningStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record FinalResultDto(List<String> finalResults) {

    public static final String DEALER_COUNT_FORMAT = "딜러: %d승 %d패";
    public static final String PLAYER_RESULT_FORMAT = "%s: %s";

    public static FinalResultDto from(Map<String, WinningStatus> playerResults) {
        long dealerWinCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.LOSE)
                .count();

        long dealerLoseCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.WIN)
                .count();

        List<String> finalResults = new ArrayList<>();
        addDealerCount(finalResults, dealerWinCount, dealerLoseCount);
        addPlayerResults(playerResults, finalResults);

        return new FinalResultDto(finalResults);
    }

    private static void addPlayerResults(Map<String, WinningStatus> playerResults, List<String> finalResults) {
        playerResults.forEach((key, value) ->
                finalResults.add(String.format(PLAYER_RESULT_FORMAT, key, value.getDescription())));
    }

    private static void addDealerCount(List<String> finalResults, long dealerWinCount, long dealerLoseCount) {
        finalResults.add(String.format(DEALER_COUNT_FORMAT, dealerWinCount, dealerLoseCount));
    }
}

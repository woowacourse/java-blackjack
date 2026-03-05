package dto;

import java.util.*;

public record FinalResultDto(List<String> finalResults) {

    public static FinalResultDto from(SortedMap<String, Boolean> playerResults) {
        long dealerWinCount = playerResults.values().stream()
                .filter(v -> v.equals(true))
                .count();

        long dealerLoseCount = playerResults.size() - dealerWinCount;

        List<String> finalResults = new ArrayList<>();
        finalResults.add(String.format("딜러: %d승 %d패", dealerWinCount, dealerLoseCount));
        playerResults.forEach((key, value) -> {
            finalResults.add(key + ": " + getWinString(value));
        });

        return new FinalResultDto(finalResults);
    }

    private static String getWinString(Boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }
}

package dto;

import domain.WinningStatus;

import java.util.*;

public record FinalResultDto(List<String> finalResults) {

    public static FinalResultDto from(SortedMap<String, WinningStatus> playerResults) {
        long dealerWinCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.WIN)
                .count();

        long dealerLoseCount = playerResults.values().stream()
                .filter(v -> v == WinningStatus.LOSE)
                .count();

        List<String> finalResults = new ArrayList<>();
        finalResults.add(String.format("딜러: %d승 %d패", dealerWinCount, dealerLoseCount));
        playerResults.forEach((key, value) -> {
            finalResults.add(key + ": " + value.getDescription());
        });

        return new FinalResultDto(finalResults);
    }

}

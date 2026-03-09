package domain.analyzer.dto;

import domain.analyzer.GameResult;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public record ResultAnalysisDto (
        EnumMap<GameResult, Integer> dealerGameResult,
        List<PlayerGameResult> playerGameResults
) {
    public static ResultAnalysisDto from(List<PlayerGameResult> playerGameResults) {
        EnumMap<GameResult, Integer> dealerGameResults = new EnumMap<>(GameResult.class);
        List<GameResult> gameResults = playerGameResults.stream()
                .map(PlayerGameResult::gameResult)
                .map(GameResult::reverseResult)
                .toList();

        for (GameResult gameResult : gameResults) {
            dealerGameResults.put(gameResult, dealerGameResults.getOrDefault(gameResult, 0) + 1);
        }

        return new ResultAnalysisDto(dealerGameResults, playerGameResults);
    }

    public String getDealerResult() {
        return Arrays.stream(GameResult.values())
                .filter(dealerGameResult::containsKey)
                .map(result -> dealerGameResult.get(result) + result.displayName())
                .collect(Collectors.joining(" "));
    }

}

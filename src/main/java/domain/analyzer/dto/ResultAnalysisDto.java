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

    private static final int ANALYSIS_INITIAL_VALUE = 0;
    private static final int INCREMENT_UNIT = 1;

    public static ResultAnalysisDto from(List<PlayerGameResult> playerGameResults) {
        EnumMap<GameResult, Integer> gameAnalysis = new EnumMap<>(GameResult.class);
        List<GameResult> gameResults = playerGameResults.stream()
                .map(PlayerGameResult::gameResult)
                .map(GameResult::reverseResult)
                .toList();

        for (GameResult result : gameResults) {
            gameAnalysis.put(result, gameAnalysis.getOrDefault(result, ANALYSIS_INITIAL_VALUE) + INCREMENT_UNIT);
        }

        return new ResultAnalysisDto(gameAnalysis, playerGameResults);
    }

    public String getDealerResult() {
        return Arrays.stream(GameResult.values())
                .filter(dealerGameResult::containsKey)
                .map(result -> dealerGameResult.get(result) + result.displayName())
                .collect(Collectors.joining(" "));
    }

}

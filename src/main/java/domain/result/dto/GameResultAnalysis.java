package domain.result.dto;

import domain.result.GameResult;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public record GameResultAnalysis(
        EnumMap<GameResult, Integer> dealerGameResult,
        List<PlayerGameResult> playerGameResults
) {
    public static GameResultAnalysis from(List<PlayerGameResult> playerGameResults) {
        EnumMap<GameResult, Integer> dealerGameResult = new EnumMap<>(GameResult.class);
        List<GameResult> list = playerGameResults.stream()
                .map(PlayerGameResult::gameResult)
                .map(GameResult::reverseResult)
                .toList();

        for (GameResult gameResult : list) {
            dealerGameResult.put(gameResult, dealerGameResult.getOrDefault(gameResult, 0) + 1);
        }

        return new GameResultAnalysis(dealerGameResult, playerGameResults);
    }

    public String getDealerResult() {
        return Arrays.stream(GameResult.values())
                .filter(dealerGameResult::containsKey) // 맵에 있는 결과만 필터링!
                .map(result -> dealerGameResult.get(result) + result.displayName())
                .collect(Collectors.joining(" "));
    }

}

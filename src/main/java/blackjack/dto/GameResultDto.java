package blackjack.dto;

import blackjack.domain.GameResult;

import java.util.List;

import static java.util.stream.Collectors.*;

public class GameResultDto {

    private final List<String> dealerGameResult;
    private final String playerGameResult;

    private GameResultDto(List<String> gameResult) {
        this.dealerGameResult = gameResult;
        this.playerGameResult = null;
    }

    private GameResultDto(String gameResult) {
        this.dealerGameResult = null;
        this.playerGameResult = gameResult;
    }

    public static GameResultDto of(List<GameResult> dealerGameResult) {
        List<String> gameResult = dealerGameResult.stream()
                .collect(groupingBy(GameResult::getName, counting()))
                .entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(toList());
        return new GameResultDto(gameResult);
    }

    public static GameResultDto of(GameResult playerGameResult) {
        String gameResult = playerGameResult.getName();
        return new GameResultDto(gameResult);
    }

    public List<String> getDealerGameResult() {
        return dealerGameResult;
    }

    public String getPlayerGameResult() {
        return playerGameResult;
    }
}

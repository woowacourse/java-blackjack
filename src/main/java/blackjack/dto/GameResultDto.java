package blackjack.dto;

import blackjack.domain.GameResult;

import java.util.List;

import static java.util.stream.Collectors.*;

public class GameResultDto {

    private final List<GameResult> dealerGameResult;
    private final GameResult playerGameresult;

    private GameResultDto(List<GameResult> gameResult) {
        this.dealerGameResult = gameResult;
        this.playerGameresult = null;
    }

    private GameResultDto(GameResult gameResult) {
        this.dealerGameResult = null;
        this.playerGameresult = gameResult;
    }

    public static GameResultDto of(List<GameResult> gameResult) {
        return new GameResultDto(gameResult);
    }

    public static GameResultDto of(GameResult gameResult) {
        return new GameResultDto(gameResult);
    }

    public String getDealerGameResult() {
        return dealerGameResult.stream()
                .collect(groupingBy(GameResult::getName, counting()))
                .entrySet().stream()
                .map(entry -> entry.getValue() + entry.getKey())
                .collect(joining(" "));
    }

    public String getPlayerGameResult() {
        return playerGameresult.getName();
    }
}

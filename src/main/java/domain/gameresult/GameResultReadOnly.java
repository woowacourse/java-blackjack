package domain.gameresult;

import java.util.HashMap;
import java.util.Map;

public class GameResultReadOnly {
    private final GameResult gameResult;

    private GameResultReadOnly(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResultReadOnly from(GameResult gameResult) {
        return new GameResultReadOnly(gameResult);
    }

    public Map<Result, Integer> getDealerResult() {
        return Map.of(
                Result.WIN, gameResult.getWinningCountOfDealer(),
                Result.LOSE, gameResult.getLosingCountOfDealer(),
                Result.DRAW, gameResult.getDrawingCountOfDealer()
        );
    }

    public Map<String, Result> getParticipantsResult() {
        HashMap<String, Result> participantResults = new HashMap<>();
        gameResult.getAllParticipantResults()
                .forEach((player, result) -> participantResults.put(player.getName(), result));
        return participantResults;
    }
}

package domain.result;

import java.util.Collections;
import java.util.Map;

public class PlayersResult {

    private static final String NULL_PLAYER_RESULT_ERROR_MESSAGE = "플레이어가 존재하지 않습니다. 잘못된 결과입니다.";
    private Map<String, Double> playerResult;

    PlayersResult(Map<String, Double> playerResult) {
        if (playerResult == null || playerResult.isEmpty()) {
            throw new NullPointerException(NULL_PLAYER_RESULT_ERROR_MESSAGE);
        }
        this.playerResult = playerResult;
    }

    public Map<String, Double> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}

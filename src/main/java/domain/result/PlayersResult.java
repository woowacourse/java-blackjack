package domain.result;

import java.util.Collections;
import java.util.List;

public class PlayersResult {

    private static final String NULL_PLAYER_RESULT_ERROR_MESSAGE = "플레이어가 존재하지 않습니다. 잘못된 결과입니다.";
    private List<PlayerResult> playersResult;

    PlayersResult(List<PlayerResult> playersResult) {
        if (playersResult == null || playersResult.isEmpty()) {
            throw new NullPointerException(NULL_PLAYER_RESULT_ERROR_MESSAGE);
        }
        this.playersResult = playersResult;
    }

    public List<PlayerResult> getPlayersResult() {
        return Collections.unmodifiableList(playersResult);
    }
}

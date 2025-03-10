package domain.participant;

import java.util.Collections;
import java.util.Map;

public class ParticipantsResult {

    private final Map<Player, GameResult> playersResult;
    private final Map<GameResult, Integer> dealerResult;

    public ParticipantsResult(Map<Player, GameResult> playersResult,
        Map<GameResult, Integer> dealerResult) {
        this.playersResult = playersResult;
        this.dealerResult = dealerResult;
    }

    public Map<Player, GameResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GameResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

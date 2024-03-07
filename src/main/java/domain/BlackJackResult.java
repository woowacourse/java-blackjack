package domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<Name, GamerResult> playersResult;

    public BlackJackResult(int dealerScore, Map<Name, Integer> playersScore) {
        this.playersResult = calculatePlayersResult(dealerScore, playersScore);
    }

    private Map<Name, GamerResult> calculatePlayersResult(int dealerScore, Map<Name, Integer> playersScore) {
        Map<Name, GamerResult> playersResult = new HashMap<>();
        playersScore.forEach((name, playerScore) -> playersResult.put(name,
                judgeResult(dealerScore, playerScore)));
        return playersResult;
    }

    private GamerResult judgeResult(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return GamerResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return GamerResult.WIN;
        }
        return GamerResult.DRAW;
    }

    public Map<Name, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }
}

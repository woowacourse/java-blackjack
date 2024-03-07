package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<Name, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(int dealerScore, Map<Name, Integer> playersScore) {
        dealerResult = new EnumMap<>(GamerResult.class);
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
            dealerResult.put(GamerResult.WIN, dealerResult.computeIfAbsent(GamerResult.WIN, score -> 0) + 1);
            return GamerResult.LOSE;
        }
        if (dealerScore < playerScore) {
            dealerResult.put(GamerResult.LOSE, dealerResult.computeIfAbsent(GamerResult.LOSE, score -> 0) + 1);
            return GamerResult.WIN;
        }
        dealerResult.put(GamerResult.DRAW, dealerResult.computeIfAbsent(GamerResult.DRAW, score -> 0) + 1);
        return GamerResult.DRAW;
    }

    public Map<Name, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

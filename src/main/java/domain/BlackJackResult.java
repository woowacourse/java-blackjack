package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlackJackResult {
    private static final int BUST_THRESHOLD = 21;
    private final Map<String, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(int dealerScore, Map<String, Integer> playersScore) {
        dealerResult = new EnumMap<>(GamerResult.class);
        this.playersResult = calculatePlayersResult(dealerScore, playersScore);
    }

    private Map<String, GamerResult> calculatePlayersResult(int dealerScore, Map<String, Integer> playersScore) {
        Map<String, GamerResult> playersResult = new HashMap<>();
        playersScore.forEach((name, playerScore) -> playersResult.put(name,
                judgeResult(dealerScore, playerScore)));
        return playersResult;
    }

    private GamerResult judgeResult(int dealerScore, int playerScore) {
        //TODO : 줄이는 방법을 찾아야 한다.
        if (playerScore > BUST_THRESHOLD) {
            dealerResult.put(GamerResult.WIN, dealerResult.computeIfAbsent(GamerResult.WIN, score -> 0) + 1);
            return GamerResult.LOSE;
        }
        if (dealerScore > BUST_THRESHOLD) {
            dealerResult.put(GamerResult.LOSE, dealerResult.computeIfAbsent(GamerResult.LOSE, score -> 0) + 1);
            return GamerResult.WIN;
        }
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

    public Map<String, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

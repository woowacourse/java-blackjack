package domain;

import domain.constant.GamerResult;
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
                getJudgePlayerResult(dealerScore, playerScore)));
        return playersResult;
    }

    private GamerResult getJudgePlayerResult(int dealerScore, int playerScore) {
        GamerResult dealerJudgeResult = GamerResult.judge(dealerScore, playerScore);
        addDealerResult(dealerJudgeResult);
        return dealerJudgeResult.getOpponentGameResult();
    }

    private void addDealerResult(GamerResult gamerResult) {
        dealerResult.put(gamerResult, dealerResult.computeIfAbsent(gamerResult, score -> 0) + 1);
    }

    public Map<Name, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

package domain;

import domain.constant.GamerResult;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlackJackResult {
    private final Map<String, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(HandStatus dealerHandStatus, Map<String, HandStatus> playersHandStatus) {
        dealerResult = new EnumMap<>(GamerResult.class);
        this.playersResult = calculatePlayersResult(dealerHandStatus, playersHandStatus);
    }

    private Map<String, GamerResult> calculatePlayersResult(HandStatus dealerHandStatus,
                                                            Map<String, HandStatus> playersHandStatus) {
        Map<String, GamerResult> playersResult = new HashMap<>();
        playersHandStatus.forEach((name, playerHandStatus) -> playersResult.put(name,
                getJudgePlayerResult(dealerHandStatus, playerHandStatus)));
        return playersResult;
    }

    private GamerResult getJudgePlayerResult(HandStatus dealerHandStatus, HandStatus playerHandStatus) {
        GamerResult playerJudgeResult = GamerResult.judgePlayerResult(dealerHandStatus, playerHandStatus);
        addDealerResult(playerJudgeResult.getOpponentGameResult());
        return playerJudgeResult;
    }

    private void addDealerResult(GamerResult gamerResult) {
        dealerResult.put(gamerResult, dealerResult.computeIfAbsent(gamerResult, score -> 0) + 1);
    }

    public Map<String, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

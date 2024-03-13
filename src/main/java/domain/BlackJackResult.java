package domain;

import domain.constant.GamerResult;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BlackJackResult {
    private final Map<Name, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(int dealerScore, Map<Name, Integer> playersScore) {
        dealerResult = calculateDealerResult(dealerScore, playersScore);
        this.playersResult = calculatePlayersResult(dealerScore, playersScore);
    }

    private Map<GamerResult, Integer> calculateDealerResult(int dealerScore, Map<Name, Integer> playersScore) {
        return playersScore.values()
                .stream()
                .collect(Collectors.groupingBy(
                        value -> getJudgePlayerResult(dealerScore, value).getOpponentGameResult(),
                        Collectors.summingInt(count -> 1)));
    }

    private Map<Name, GamerResult> calculatePlayersResult(int dealerScore, Map<Name, Integer> playersScore) {
        return playersScore.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> getJudgePlayerResult(dealerScore, entry.getValue())));
    }

    private GamerResult getJudgePlayerResult(int dealerScore, int playerScore) {
        GamerResult dealerJudgeResult = GamerResult.judge(dealerScore, playerScore);
        return dealerJudgeResult.getOpponentGameResult();
    }

    public Map<Name, GamerResult> getPlayersResult() {
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<GamerResult, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}

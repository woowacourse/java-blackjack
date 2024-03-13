package domain;

import domain.constant.GamerResult;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BlackJackResult {
    private final Map<Name, GamerResult> playersResult;
    private final Map<GamerResult, Integer> dealerResult;

    public BlackJackResult(Dealer dealer, Players players) {
        dealerResult = calculateDealerResult(dealer, players);
        this.playersResult = calculatePlayersResult(dealer, players);
    }

    private Map<GamerResult, Integer> calculateDealerResult(Dealer dealer, Players players) {
        return players.getPlayersTotalScores()
                .values()
                .stream()
                .collect(Collectors.groupingBy(
                        value -> getJudgePlayerResult(dealer.getTotalScore(), value).getOpponentGameResult(),
                        Collectors.summingInt(count -> 1)));
    }

    private Map<Name, GamerResult> calculatePlayersResult(Dealer dealer, Players players) {
        return players.getPlayersTotalScores()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        entry -> getJudgePlayerResult(dealer.getTotalScore(), entry.getValue())));
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

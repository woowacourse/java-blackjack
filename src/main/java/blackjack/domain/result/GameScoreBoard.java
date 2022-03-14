package blackjack.domain.result;

import blackjack.domain.participant.Participant;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GameScoreBoard {

    private final Map<Result, Integer> dealerGameResult;
    private final Map<String, String> playerGameResultMap;

    public GameScoreBoard(Map<Result, Integer> dealerGameResult,
        Map<String, String> playerGameResultMap) {
        this.dealerGameResult = dealerGameResult;
        this.playerGameResultMap = playerGameResultMap;
    }

    public static GameScoreBoard recordGameScore(Participant dealer, List<Participant> players) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        Map<String, String> playerResult = new TreeMap<>();
        for (Participant player : players) {
            Result playerGameScore = Result.findResult(player, dealer);
            Result dealerGameScore = playerGameScore.reverse();
            dealerResult.put(dealerGameScore, dealerResult.getOrDefault(dealerGameScore, 0) + 1);
            playerResult.put(player.getName(), playerGameScore.getName());
        }
        return new GameScoreBoard(dealerResult, playerResult);
    }

    public Map<String, String> getPlayerGameResultMap() {
        return Collections.unmodifiableMap(playerGameResultMap);
    }

    public Map<Result, Integer> getDealerGameResult() {
        return Collections.unmodifiableMap(dealerGameResult);
    }
}

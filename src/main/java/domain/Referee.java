package domain;

import domain.dto.GameResultDto;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Referee {

    public GameResultDto createGameResult(int dealerScore, Map<String, Integer> scoreByPlayer) {
        Map<String, MatchResult> playerResults = calculatePlayerResults(dealerScore, scoreByPlayer);
        EnumMap<MatchResult, Integer> dealerResults = calculateDealerResults(playerResults);

        return new GameResultDto(dealerResults, playerResults);
    }

    private Map<String, MatchResult> calculatePlayerResults(int dealerScore, Map<String, Integer> scoreByPlayer) {
        Map<String, MatchResult> results = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : scoreByPlayer.entrySet()) {
            results.put(entry.getKey(), judge(dealerScore, entry.getValue()));
        }
        return results;
    }

    private EnumMap<MatchResult, Integer> calculateDealerResults(Map<String, MatchResult> playerResults) {
        EnumMap<MatchResult, Integer> counts = new EnumMap<>(MatchResult.class);
        for (MatchResult result : playerResults.values()) {
            counts.merge(result.reverse(), 1, Integer::sum);
        }
        return counts;
    }

    private MatchResult judge(int dealerScore, int playerScore) {
        if (isBust(playerScore)) {
            return MatchResult.LOSE;
        }
        if (isBust(dealerScore)) {
            return MatchResult.WIN;
        }

        return compareScore(dealerScore, playerScore);
    }

    private MatchResult compareScore(int dealerScore, int playerScore) {
        if (dealerScore > playerScore) {
            return MatchResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }

    private boolean isBust(int score) {
        return score > 21;
    }
}

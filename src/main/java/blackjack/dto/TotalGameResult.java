package blackjack.dto;

import blackjack.domain.result.JudgeResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TotalGameResult {

    private final List<ResultCount> dealerResultCounts;
    private final List<PlayerResult> playerResults;

    private TotalGameResult(final List<ResultCount> dealerResultCounts, final List<PlayerResult> playerResults) {
        this.dealerResultCounts = dealerResultCounts;
        this.playerResults = playerResults;
    }

    public static TotalGameResult of(final Map<JudgeResult, Integer> countByDealerJudgeResults,
                                     final Map<String, JudgeResult> judgeResultsByPlayer) {
        return new TotalGameResult(dealerResultCountsFrom(countByDealerJudgeResults),
                playerResultsFrom(judgeResultsByPlayer));
    }

    private static List<ResultCount> dealerResultCountsFrom(final Map<JudgeResult, Integer> countByDealerJudgeResults) {
        return countByDealerJudgeResults.entrySet()
                .stream()
                .map(entry -> new ResultCount(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private static List<PlayerResult> playerResultsFrom(final Map<String, JudgeResult> judgeResultsByPlayer) {
        return judgeResultsByPlayer.entrySet()
                .stream()
                .map(entry -> new PlayerResult(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<ResultCount> getDealerResultCounts() {
        return dealerResultCounts;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }
}

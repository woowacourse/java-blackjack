package blackjack.domain;

import blackjack.domain.result.JudgeResult;
import blackjack.dto.TotalGameResult;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameResultComputer {

    private final Map<String, JudgeResult> judgeResultsByPlayer;

    public GameResultComputer(final Map<String, JudgeResult> judgeResultsByPlayer) {
        this.judgeResultsByPlayer = judgeResultsByPlayer;
    }

    public Map<JudgeResult, Integer> countDealerJudgeResults() {
        return judgeResultsByPlayer.values()
                .stream()
                .map(JudgeResult::counter)
                .collect(Collectors.toMap(Function.identity(), result -> 1, Integer::sum,
                        () -> new EnumMap<>(JudgeResult.class)));
    }

    public TotalGameResult computeTotalResult() {
        return TotalGameResult.of(countDealerJudgeResults(), judgeResultsByPlayer);
    }
}

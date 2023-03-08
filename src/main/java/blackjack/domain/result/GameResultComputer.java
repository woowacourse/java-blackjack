package blackjack.domain.result;

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

    public Map<String, JudgeResult> getJudgeResultsByPlayer() {
        return judgeResultsByPlayer;
    }
}

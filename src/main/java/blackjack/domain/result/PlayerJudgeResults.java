package blackjack.domain.result;

import java.util.Map;

public class PlayerJudgeResults {

    private final Map<String, JudgeResult> judgeResultsByPlayer;

    public PlayerJudgeResults(final Map<String, JudgeResult> judgeResultsByPlayer) {
        this.judgeResultsByPlayer = judgeResultsByPlayer;
    }

    public int collectCountByJudgeResult(final JudgeResult judgeResult) {
        return (int) judgeResultsByPlayer.values()
                .stream()
                .filter(result -> result == judgeResult)
                .count();
    }

    public Map<String, JudgeResult> getJudgeResultsByPlayer() {
        return judgeResultsByPlayer;
    }
}

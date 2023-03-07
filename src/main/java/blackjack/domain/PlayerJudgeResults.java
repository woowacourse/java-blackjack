package blackjack.domain;

import java.util.Map;

public class PlayerJudgeResults {

    private final Map<String, JudgeResult> judgeResultsByPlayer;

    public PlayerJudgeResults(Map<String, JudgeResult> judgeResultsByPlayer) {
        this.judgeResultsByPlayer = judgeResultsByPlayer;
    }

    public int collectCountByJudgeResult(JudgeResult judgeResult) {
        return (int) judgeResultsByPlayer.values()
                .stream()
                .filter(result -> result == judgeResult)
                .count();
    }

    public Map<String, JudgeResult> getJudgeResultsByPlayer() {
        return judgeResultsByPlayer;
    }
}

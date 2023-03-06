package blackjack.domain;

import static blackjack.domain.JudgeResult.LOSE;
import static blackjack.domain.JudgeResult.PUSH;
import static blackjack.domain.JudgeResult.WIN;

import java.util.Map;

public class PlayerJudgeResults {

    private final Map<String, JudgeResult> judgeResultsByPlayer;

    public PlayerJudgeResults(Map<String, JudgeResult> judgeResultsByPlayer) {
        this.judgeResultsByPlayer = judgeResultsByPlayer;
    }

    public int countDealerWins() {
        return countByJudgeResult(LOSE);
    }

    public int countDealerPushes() {
        return countByJudgeResult(PUSH);
    }

    public int countDealerLoses() {
        return countByJudgeResult(WIN);
    }

    private int countByJudgeResult(JudgeResult judgeResult) {
        return (int) judgeResultsByPlayer.values()
                .stream()
                .filter(result -> result == judgeResult)
                .count();
    }

    public Map<String, JudgeResult> getJudgeResultsByPlayer() {
        return judgeResultsByPlayer;
    }
}

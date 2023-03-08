package blackjack.dto;

import blackjack.domain.result.JudgeResult;

public class PlayerResult {

    private final String name;
    private final JudgeResult judgeResult;

    public PlayerResult(final String name, final JudgeResult judgeResult) {
        this.name = name;
        this.judgeResult = judgeResult;
    }

    public String getName() {
        return name;
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }
}

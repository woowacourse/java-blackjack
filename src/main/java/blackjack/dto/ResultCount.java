package blackjack.dto;

import blackjack.domain.result.JudgeResult;

public class ResultCount {

    private final JudgeResult judgeResult;
    private final int count;

    public ResultCount(final JudgeResult judgeResult, final int count) {
        this.judgeResult = judgeResult;
        this.count = count;
    }

    public JudgeResult getJudgeResult() {
        return judgeResult;
    }

    public int getCount() {
        return count;
    }
}

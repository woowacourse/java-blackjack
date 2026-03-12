package domain;

import java.util.List;

public class TotalFinalResult {
    private final List<FinalResult> totalResult;

    private TotalFinalResult(List<FinalResult> totalResult) {
        this.totalResult = totalResult;
    }

    public static TotalFinalResult from(List<FinalResult> totalResult) {
        return new TotalFinalResult(totalResult);
    }

    public List<FinalResult> getTotalResult() {
        return totalResult;
    }
}

package domain;

public enum MatchResult {
    WIN("승"),
    DRAW("무"),
    LOSE("패")
    ;

    private final String resultName;

    MatchResult(String resultName) {
        this.resultName = resultName;
    }

    public String getResultName() {
        return resultName;
    }
}

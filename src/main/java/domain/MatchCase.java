package domain;

public enum MatchCase {
    WIN("승"), LOSE("패"), DRAW("무");

    private final String korResult;

    MatchCase(String korResult) {
        this.korResult = korResult;
    }

    public String getKorResult() {
        return korResult;
    }
}

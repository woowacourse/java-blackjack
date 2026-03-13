package domain;

public enum MatchCase {
    WIN("승","패"), DRAW("무","무"), LOSE("패","승");

    private final String korResult;
    private final String reverseResult;

    MatchCase(String korResult, String reverseResult) {
        this.korResult = korResult;
        this.reverseResult = reverseResult;
    }

    public String getKorResult() {
        return korResult;
    }

    public String getReversedKorResult() {
        return reverseResult;
    }
}

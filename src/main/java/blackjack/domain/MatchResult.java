package blackjack.domain;

public enum MatchResult {
    WIN("승"),
    TIE("무승부"),
    LOSE("패"),
    ;

    private String message;

    MatchResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

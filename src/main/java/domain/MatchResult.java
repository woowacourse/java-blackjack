package domain;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String korean;

    MatchResult(String korean) {
        this.korean = korean;
    }
}

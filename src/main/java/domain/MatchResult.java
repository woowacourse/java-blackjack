package domain;

public enum MatchResult {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    MatchResult(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
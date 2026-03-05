package domain;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(String description) {
        this.description = description;
    }
}

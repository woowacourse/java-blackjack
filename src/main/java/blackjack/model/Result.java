package blackjack.model;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private final String name;

    Result(final String name) {
        this.name = name;
    }
}

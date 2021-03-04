package blackjack.domain;

public enum ResultType {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    ResultType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

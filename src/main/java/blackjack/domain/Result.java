package blackjack.domain;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    private String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package blackjack.domain;

public enum Result {

    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public Result reverse() {
        if (LOSE == this) {
            return WIN;
        }
        if (WIN == this) {
            return LOSE;
        }
        return this;
    }

    public String getName() {
        return name;
    }
}

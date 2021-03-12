package blackjack.domain;

public enum ResultType {
    // TODO 블랙잭 우승의 경우 필요
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

    public ResultType reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}

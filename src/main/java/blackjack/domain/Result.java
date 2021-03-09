package blackjack.domain;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Result replaceWinWithLose() {
        if (this == LOSE) {
            return WIN;
        }
        if (this == WIN) {
            return LOSE;
        }
        return DRAW;
    }
}

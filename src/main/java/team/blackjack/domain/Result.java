package team.blackjack.domain;

public enum Result {
    BLACKJACK("블랙잭"),
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(String name) {

        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Result reverse() {
        if (this == BLACKJACK) {
            return LOSE;
        }
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}

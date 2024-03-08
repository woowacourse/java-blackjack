package domain.blackjackgame;

public enum ResultStatus {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    ResultStatus(String name) {
        this.name = name;
    }

    public ResultStatus reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }

    public String getName() {
        return this.name;
    }
}

package team.blackjack.domain;

public enum Result {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(String name) {

        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public Result reverse(){
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}

package domain.constant;

public enum Result {
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    BLACKJACK("블랙잭", 1.5),
    BUST("버스트", -1);

    private final String name;
    private final double allocation;

    Result(String name, double allocation) {
        this.name = name;
        this.allocation = allocation;
    }

    public double getAllocation() {
        return allocation;
    }
}

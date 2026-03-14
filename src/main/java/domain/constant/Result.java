package domain.constant;

public enum Result {
    WIN(1),
    DRAW(0),
    LOSE(-1),
    BLACKJACK(1.5),
    BUST(-1);

    private double allocation;

    Result(double allocation) {
        this.allocation = allocation;
    }

    public String getName() {
        return name + " ";
    }

    public double getAllocation() {
        return allocation;
    }
}

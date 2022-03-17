package blackjack.model;

public enum Result {
    WIN(1),
    LOSE(-1),
    TIE(0),
    BLACKJACK(1.5),
    ;

    private final double houseEdge;

    Result(double houseEdge) {
        this.houseEdge = houseEdge;
    }

    public Money apply(Money money) {
        return money.multipliedBy(this.houseEdge);
    }
}

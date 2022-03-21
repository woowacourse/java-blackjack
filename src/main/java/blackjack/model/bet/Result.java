package blackjack.model.bet;

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

    public Profit apply(Bet bet) {
        return new Profit(bet.multipliedAmountBy(this.houseEdge));
    }
}

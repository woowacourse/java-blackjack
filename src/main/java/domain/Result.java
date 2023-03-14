package domain;

public enum Result {

    WIN(1),
    TIE(0),
    LOSE(-1),
    BLACKJACK(1.5);

    private final double odds;

    Result(double odds) {
        this.odds = odds;
    }

    public int calculateResult(int bettingMoney) {
        return (int) (bettingMoney * odds);
    }
}

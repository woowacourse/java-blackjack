package domain;

public enum Result {
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    BLACKJACKWIN(1.5);

    private final double profitRate;

    Result(double profitRate) {
        this.profitRate = profitRate;
    }

    public int calculateResult(int betMoney){
        return (int) (betMoney * profitRate);
    }
}

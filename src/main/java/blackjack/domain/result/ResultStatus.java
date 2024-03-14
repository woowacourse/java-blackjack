package blackjack.domain.result;

public enum ResultStatus {
    WIN(1),
    LOSE(-1),
    DRAW(0),
    BLACKJACK(1.5);

    private double rate;
    
    ResultStatus(double rate) {
        this.rate = rate;
    }


    public double calculateProfit(int battingAmount) {
        return rate * battingAmount;
    }
}

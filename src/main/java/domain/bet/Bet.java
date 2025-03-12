package domain.bet;

public class Bet {

    private final double amount;

    public Bet(final double amount) {
        this.amount = amount;
    }

    public double applyBlackJackBonus() {
        return amount * 1.5;
    }

    public double applyDealerBustBonus() {
        return amount;
    }
}

package domain.bet;

public class Bet {

    private final double amount;

    public Bet(final double amount) {
        this.amount = amount;
    }

    public Bet applyBlackJackBonus() {
        return new Bet(amount * 1.5);
    }

    public Bet applyDealerBustBonus() {
        return new Bet(amount * 2);
    }

    public Bet applyPlayerBustPenalty() {
        return new Bet(10000);
    }

    public double getAmount() {
        return amount;
    }
}

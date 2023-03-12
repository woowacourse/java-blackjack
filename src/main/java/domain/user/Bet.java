package domain.user;

public class Bet {

    private static final double REVENUE_BONUS_RATE = 1.5;

    private int credit;
    private int revenue;

    public Bet(final int credit) {
        this.credit = credit;
        this.revenue = 0;
    }

    public void addAmount(final int amount) {
        this.credit += amount;
    }

    public void addRevenue() {
        revenue += credit;
    }

    public void addBonusRevenue() {
        revenue += credit * REVENUE_BONUS_RATE;
    }

    public void decreaseRevenue() {
        revenue -= credit;
    }

    public void payFor(final Bet otherBet) {
        revenue -= otherBet.revenue;
    }

    public int getRevenue() {
        return revenue;
    }
}

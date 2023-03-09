package domain.user;

public class Bet {

    private static final double REVENUE_BONUS_RATE = 1.5;

    private int credit;
    private int revenue;

    public Bet(final int credit) {
        this.credit = credit;
        this.revenue = 0;
    }

    public void addAmount(int amount) {
        this.credit += amount;
    }

    public void takeRevenueFrom(final Bet otherBet) { // TODO: REVENUE 객체 분리
        this.revenue += otherBet.credit;
        otherBet.revenue -= otherBet.credit;
    }

    public void takeBonusRevenueFrom(final Bet otherBet) {
        otherBet.revenue -= otherBet.credit * REVENUE_BONUS_RATE;
        this.revenue += otherBet.credit * REVENUE_BONUS_RATE;
    }

    public int getRevenue() {
        return revenue;
    }
}

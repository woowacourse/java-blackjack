package domain.user;

public class Bet {

    private static final int MIN_BET_AMOUNT = 1;
    private static final double REVENUE_BONUS_RATE = 1.5;

    private static final String BET_AMOUNT_SMALL_MESSAGE =
            String.format("[ERROR] 배팅 금액은 %d이상이어야 합니다.", MIN_BET_AMOUNT);

    private int credit;
    private int revenue;

    public Bet(final int credit) {
        validate(credit);
        this.credit = credit;
        this.revenue = 0;
    }

    private void validate(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(BET_AMOUNT_SMALL_MESSAGE);
        }
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

package participant;

public class Wallet {

    private final Money bettingMoney;
    private Money earnedMoney;
    private Profit profit;

    public Wallet(Money earnedMoney, Money bettingMoney, Profit profit) {
        this.earnedMoney = earnedMoney;
        this.bettingMoney = bettingMoney;
        this.profit = profit;
    }

    public static Wallet of(int bettingMoney) {
        return new Wallet(Money.of(0), Money.of(bettingMoney), Profit.of(0));
    }

    public void updateMoney(double rate) {
        this.earnedMoney = bettingMoney.add(bettingMoney.multiply(rate).getAmount());
        this.profit = Profit.from(bettingMoney, earnedMoney);
    }

    public int getEarnedMoney() {
        return earnedMoney.getAmount();
    }

    public Profit getProfit() {
        return profit;
    }
}

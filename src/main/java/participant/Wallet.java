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

    public void addMoney(int money) {
        this.earnedMoney = bettingMoney.add(money);
        this.profit = Profit.from(bettingMoney, earnedMoney);
    }

    public void subtractMoney(int money) {
        this.earnedMoney = bettingMoney.minus(money);
        this.profit = Profit.from(bettingMoney, earnedMoney);
    }

    public int getBettingMoney() {
        return bettingMoney.getAmount();
    }

    public int getEarnedMoney() {
        return earnedMoney.getAmount();
    }

    public Profit getProfit() {
        return profit;
    }
}

package participant;

public class Profit {

    private final int amount;

    private Profit(int amount) {
        this.amount = amount;
    }

    public static Profit of(int amount) {
        return new Profit(amount);
    }

    public static Profit from(Money bettingMoney, Money earnedMoney) {
        return new Profit(earnedMoney.minus(bettingMoney.getAmount()).getAmount());
    }

    public Profit reverse() {
        return new Profit(this.amount * -1);
    }

    public int getAmount() {
        return amount;
    }
}

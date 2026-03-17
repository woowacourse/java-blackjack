package domain.money;

public class EarnedMoney extends Money {
    private EarnedMoney(long amount) {
        super(amount);
    }

    public static EarnedMoney from(long amount) {
        return new EarnedMoney(amount);
    }
}

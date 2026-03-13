package model.vo;

public class BetAmount {

    private static final int DEFAULT_BETTING_AMOUNT = 0;
    private final int amount;

    public BetAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static BetAmount of(Integer number) {
        return new BetAmount(number);
    }

    private void validate(int amount) {
        if (amount < DEFAULT_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 음수가 될 수 없습니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}

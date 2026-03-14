package domain;

public class BettingAmount {
    private final Integer money;

    public BettingAmount(Integer bettingAmount) {
        validateMinus(bettingAmount);
        this.money = bettingAmount;
    }

    private static void validateMinus(Integer bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수일 없습니다.");
        }
    }

    public Integer getMoney() {
        return money;
    }

    public static BettingAmount zero() {
        return new BettingAmount(0);
    }

    public BettingAmount doubleAmount() {
        return new BettingAmount(money * 2);
    }
}

package model.participant;

public final class BettingAmount {
    private static final int DIVIDE_UNIT = 100;

    private final int amount;

    private BettingAmount(int amount) {
        this.amount = amount;
    }

    public static BettingAmount from(int amount) {
        validateAmount(amount);

        return new BettingAmount(amount);
    }

    private static void validateAmount(int amount) {
        validatePositive(amount);
        validateDivideByUnit(amount);
    }

    private static void validateDivideByUnit(int amount) {
        if (amount % DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 " + DIVIDE_UNIT + "원 단위여야 합니다.");
        }
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0원보다 커야 합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}

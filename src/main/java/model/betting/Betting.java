package model.betting;

import model.Status;

public class Betting implements Bettable{
    private static final int EXCLUDE_MINIMUM_BETTING = 0;
    public static final String BETTING_AMOUNT_LOWER_BOUND_MESSAGE =
            "베팅 금액는 " + EXCLUDE_MINIMUM_BETTING + "원보다 커야합니다.";
    private static final String BETTING_AMOUNT_NEGATIVE_MESSAGE = "베팅이 음수가 될 수 없습니다.";
    public static final Betting INIT_BETTING = new Betting(0);

    private final long bettingAmount;

    private Betting(long bettingAmount) {
        checkBettingAmountIsNotNegative(bettingAmount);
        this.bettingAmount = bettingAmount;
    }

    public static Betting of(long bettingAmount) {
        checkBettingAmountPositive(bettingAmount);
        return new Betting(bettingAmount);
    }

    private static void checkBettingAmountIsNotNegative(long bettingAmount) {
        if (bettingAmount < EXCLUDE_MINIMUM_BETTING) {
            throw new IllegalArgumentException(BETTING_AMOUNT_NEGATIVE_MESSAGE);
        }
    }

    private static void checkBettingAmountPositive(long bettingAmount) {
        if (bettingAmount <= EXCLUDE_MINIMUM_BETTING) {
            throw new IllegalArgumentException(BETTING_AMOUNT_LOWER_BOUND_MESSAGE);
        }
    }

    public long calculateBetting(Status status) {
        return status.calculateWinMargin(bettingAmount);
    }
}

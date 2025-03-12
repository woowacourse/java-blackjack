package blackjack;

public class BettingMachine {

    private static final int UNIT_BETTING_MONEY = 100;
    private final long bettingMoney;
    private final long earnedMoney;

    public BettingMachine() {
        this.bettingMoney = 0;
        this.earnedMoney = 0;
    }

    public void bet(final String bettingAmount) {
        validateBettingAmount(bettingAmount);
    }

    private void validateBettingAmount(final String bettingAmount) {
        validateIsDigit(bettingAmount);
        validateIsUnitSize(bettingAmount);
    }

    private void validateIsDigit(final String bettingAmount) {
        if (bettingAmount == null || bettingAmount.isEmpty()) {
            throw new IllegalArgumentException("빈 값을 입력했습니다.");
        }
        try {
            int bettingMoney = Integer.parseInt(bettingAmount);
            validateIsPositiveDigit(bettingMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다.");
        }
    }

    private static void validateIsPositiveDigit(final int bettingMoney) {
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("음수를 입력했습니다.");
        }
    }

    private void validateIsUnitSize(final String bettingAmount) {
        int bettingMoney = Integer.parseInt(bettingAmount);
        if (bettingMoney % UNIT_BETTING_MONEY != 0) {
            throw new IllegalArgumentException("100원 단위가 아닙니다.");
        }
    }
}

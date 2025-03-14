package blackjack.bettingMachine;

public class BettingMachine {

    private static final int UNIT_BETTING_MONEY = 100;

    private final Money bettingMoney;
    private final Money earnedMoney;

    public BettingMachine() {
        this.bettingMoney = new Money(0);
        this.earnedMoney = new Money(0);
    }

    public void bet(final String bettingAmount) {
        validateBettingAmount(bettingAmount);
        this.bettingMoney.increase(Integer.parseInt(bettingAmount));
    }

    public void earnDouble() {
        earnedMoney.increase(bettingMoney.getDouble());
    }

    public void earnSingle() {
        earnedMoney.increase(bettingMoney.getMoney());
    }

    public void earnOneAndHalf() {
        earnedMoney.increase(bettingMoney.getOneAndHalf());
    }

    public long getProfit() {
        return earnedMoney.getMoney() - bettingMoney.getMoney();
    }
    
    private void validateBettingAmount(final String bettingAmount) {
        validateIsDigit(bettingAmount);
        validateIsUnitSize(bettingAmount);
    }

    private void validateIsDigit(final String bettingAmount) {
        if (bettingAmount == null || bettingAmount.isEmpty()) {
            throw new IllegalArgumentException("빈 값을 입력했습니다. 다시 입력해주세요.");
        }
        try {
            int bettingMoney = Integer.parseInt(bettingAmount);
            validateIsPositiveDigit(bettingMoney);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다. 다시 입력해주세요.");
        }
    }

    private static void validateIsPositiveDigit(final int bettingMoney) {
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("음수를 입력했습니다. 다시 입력해주세요.");
        }
    }

    private void validateIsUnitSize(final String bettingAmount) {
        int bettingMoney = Integer.parseInt(bettingAmount);
        if (bettingMoney % UNIT_BETTING_MONEY != 0) {
            throw new IllegalArgumentException("100원 단위가 아닙니다. 다시 입력해주세요.");
        }
    }
}

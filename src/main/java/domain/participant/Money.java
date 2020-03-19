package domain.participant;

public class Money {
    private int money;

    private Money(String money) {
        this.money = validateMoney(money);
    }

    public static Money create(String money) {
        return new Money(money);
    }

    private int validateMoney(String money) {
        int moneyAmount = getValidatedMoney(money);
        validateAmount(moneyAmount);
        return moneyAmount;
    }

    private void validateAmount(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("베팅 금액은 음수가 아니어야 합니다.");
        }
    }

    private int getValidatedMoney(String money) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("올바른 형식의 입력이 아닙니다.");
        }
    }
}

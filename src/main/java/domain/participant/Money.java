package domain.participant;

public class Money {

    private static final String CANT_INIT_MONEY_NEGATIVE_ERROR_MESSAGE = "[Error] 음수인 값으로 초기화 할 수 없습니다.";

    private int money;

    public Money(int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(int money) {
        if (money < 0) {
            throw new IllegalArgumentException(CANT_INIT_MONEY_NEGATIVE_ERROR_MESSAGE);
        }
    }

    public int getMoney() {
        return money;
    }
}

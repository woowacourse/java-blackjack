package blackjack.domain;

public class Money {

    public static final String ERROR_VALIDATE_MONEY = "올바름 금액을 입력하여 주세요";

    private int money;

    public Money(String enterMoney) {
        validateMoney(enterMoney);
        this.money = Integer.parseInt(enterMoney);
    }

    public Money(int money) {
        this.money = money;
    }


    private void validateMoney(String bettingMoney) {
        try {
            Integer.parseInt(bettingMoney);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ERROR_VALIDATE_MONEY);
        }
    }

    public int getMoney() {
        return money;
    }

    public void giveMoney(Money plusMoney) {
        money += plusMoney.getMoney();
    }
}

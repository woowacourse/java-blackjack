package domain;

public class Money {
    private final int money;

    public Money(final String money) {
        this.money = validateNumber(money);
    }

    private int validateNumber(String inputMoney) {
        try {
            return Integer.parseInt(inputMoney);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException();
        }
    }
}

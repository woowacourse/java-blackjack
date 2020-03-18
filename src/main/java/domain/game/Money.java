package domain.game;

import java.math.BigDecimal;

public class Money {
    private BigDecimal money;

    public Money(String money) {
        validate(money);

        this.money = new BigDecimal(money);
    }

    private void validate(String inputString) {
        char[] inputCharacters = inputString.toCharArray();

        validateDigit(inputCharacters);
    }

    private void validateDigit(char[] inputCharacters) {
        for (char inputCharacter : inputCharacters) {
            if (!Character.isDigit(inputCharacter)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public void addMoney(BigDecimal money) {
        this.money = this.money.add(money);
    }

    public void subtractMoney(BigDecimal money) {
        this.money = this.money.subtract(money);
    }

    public BigDecimal getMoney() {
        return money;
    }
}

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

        for (char inputCharacter : inputCharacters) {
            validateDigit(inputCharacter);
        }
    }

    private void validateDigit(char inputCharacter) {
        if (!Character.isDigit(inputCharacter)) {
            throw new IllegalArgumentException();
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

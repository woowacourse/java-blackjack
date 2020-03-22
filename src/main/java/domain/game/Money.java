package domain.game;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal money;

    public Money(String money) {
        validate(money);
        this.money = new BigDecimal(money);
    }

    public Money(BigDecimal money) {
        this.money = money;
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

    public Money addMoney(BigDecimal money) {
        return new Money(this.money.add(money));
    }

    public BigDecimal getMoney() {
        return money;
    }
}

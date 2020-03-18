package domain.betting;

import java.math.BigDecimal;

public class BettingMoney {
    private BigDecimal bettingMoney;

    public BettingMoney(String bettingMoney) {
        validate(bettingMoney);

        this.bettingMoney = new BigDecimal(bettingMoney);
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

    public void addBettingMoney(BigDecimal money) {
        bettingMoney = bettingMoney.add(money);
    }

    public void subtractBettingMoney(BigDecimal money) {
        bettingMoney = bettingMoney.subtract(money);
    }

    public BigDecimal getBettingMoney() {
        return bettingMoney;
    }
}

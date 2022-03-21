package blackjack.domain.bet;

import java.util.Objects;

public class BetMoney {

    private static final String BETTING_MONEY_ERROR_MESSAGE = "베팅 금액으로 1 이상 1,000,000 이하의 숫자를 입력해주세요.";
    private final int money;

    public BetMoney(String money) {
        validateMoney(money);
        this.money = Integer.parseInt(money);
    }

    private void validateMoney(String money) {
        validateInteger(money);
        validateMoneyRange(Integer.parseInt(money));
    }

    private void validateInteger(String money) {
        try {
            Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BETTING_MONEY_ERROR_MESSAGE);
        }
    }

    private void validateMoneyRange(int money) {
        if (money < 1 || money > 1_000_000) {
            throw new IllegalArgumentException(BETTING_MONEY_ERROR_MESSAGE);
        }
    }

    public int get() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BetMoney)) {
            return false;
        }
        BetMoney betMoney = (BetMoney) o;
        return money == betMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

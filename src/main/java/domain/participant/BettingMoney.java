package domain.participant;

import java.util.Objects;

public class BettingMoney {

    private static final int MAX_MONEY = 10000;
    private static final int MIN_MONEY = 1000;
    private static final int UNIT_OF_MONEY = 1000;

    private final int money;

    public BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < MIN_MONEY || MAX_MONEY < money) {
            throw new IllegalArgumentException(String.format("배팅 금액은 최소 %s원 최대 %s원 입니다.", MIN_MONEY, MAX_MONEY));
        }

        if (money % UNIT_OF_MONEY != 0) {
            throw new IllegalArgumentException(String.format("배팅금액은 %s원 단위로 입력해야합니다.", UNIT_OF_MONEY));
        }
    }

    public int getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        BettingMoney otherBettingMoney = (BettingMoney) other;
        return money == otherBettingMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}

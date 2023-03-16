package blackjack.domain.participants;

class BettingMoney {

    private static final int emptyValue = 0;
    private static final int unitValue = 1000;
    private static final String ERROR_NOT_UNIT_BETTING_MONEY = String.format("베팅 금액은 %d원 단위여야 합니다.", unitValue);
    private static final Money emptyMoney = new Money(emptyValue);
    private static final Money unitMoney = new Money(unitValue);
    private final Money money;

    BettingMoney(Money money) {
        validateBettingMoney(money);
        this.money = money;
    }

    BettingMoney() {
        this(emptyMoney);
    }

    private void validateBettingMoney(Money inputMoney) {
        validateUnitBettingMoney(inputMoney);
        validatePositiveBettingMoney(inputMoney);
    }

    private static void validateUnitBettingMoney(Money inputMoney) {
        if(!inputMoney.divide(unitMoney).equals(new Money(0)))
            throw new IllegalArgumentException(ERROR_NOT_UNIT_BETTING_MONEY);
    }

    private void validatePositiveBettingMoney(Money inputMoney) {
        if(!inputMoney.isPositiveMoney()) throw new IllegalArgumentException(ERROR_NOT_UNIT_BETTING_MONEY);
    }

    Money getMoney() {
        return money;
    }
}

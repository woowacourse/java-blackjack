package blackjack.domain.participants;

public class BettingMoney {

    private static final int MIN_VALUE = 0;
    private static final int UNIT_VALUE = 1000;
    private static final String ERROR_NOT_UNIT_BETTING_MONEY = String.format("베팅 금액은 %d원 단위여야 합니다.", UNIT_VALUE);
    private static final Money minimumBettingMoney = new Money(MIN_VALUE);
    private static final Money unitBettingMoney = new Money(UNIT_VALUE);
    private final Money money;

    private BettingMoney(Money money) {
        validateBettingMoney(money);
        this.money = money;
    }

    public BettingMoney(int money) {
        this(new Money(money));
    }

    private static void validateUnitBettingMoney(Money inputMoney) {
        if (!inputMoney.isDividedBy(unitBettingMoney)) {
            throw new IllegalArgumentException(ERROR_NOT_UNIT_BETTING_MONEY);
        }
    }

    private void validateBettingMoney(Money inputMoney) {
        validateUnitBettingMoney(inputMoney);
        validateMinimumBettingMoney(inputMoney);
    }

    private void validateMinimumBettingMoney(Money inputMoney) {
        if (!inputMoney.isBiggerThan(minimumBettingMoney)) {
            throw new IllegalArgumentException(ERROR_NOT_UNIT_BETTING_MONEY);
        }
    }

    Money getMoney() {
        return money;
    }
}

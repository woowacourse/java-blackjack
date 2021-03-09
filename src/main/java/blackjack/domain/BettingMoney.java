package blackjack.domain;

public class BettingMoney {
    private static final int MIN_BETTING_MONEY_BOUND = 1_000;
    private static final int MAX_BETTING_MONEY_BOUND = 100_000_000;

    private final int value;

    public BettingMoney(int value) {
        validate(value);
        this.value = value;
    }

    public BettingMoney() {
        this(MIN_BETTING_MONEY_BOUND);
    }

    private void validate(int value) {
        if (!(MIN_BETTING_MONEY_BOUND <= value && value <= MAX_BETTING_MONEY_BOUND)) {
            throw new IllegalArgumentException("배팅 금액은 1억원을 초과할 수 없습니다.");
        }
    }

    public int getMoney() {
        return value;
    }
}

package domain.betting;

class Bet {
    private static final int MAXIMUM_BETTING_MONEY = 1_000_000;
    private static final int MINIMUM_BETTING_MONEY = 1_000;
    static final String INVALID_BETTING_MESSAGE = "베팅액은 최소 천원, 최대 백만원입니다.";

    private final int money;

    public Bet(final int money) {
        validateRange(money);
        this.money = money;
    }

    public void validateRange(final int money) {
        if (money < MINIMUM_BETTING_MONEY || MAXIMUM_BETTING_MONEY < money) {
            throw new IllegalArgumentException(INVALID_BETTING_MESSAGE);
        }
    }
}

package domain.game;

public record BettingMoney(int value) {
    private static final int MINIMUM_BETTING_MONEY = 10000;

    public BettingMoney {
        validateMoney(value);
    }

    private void validateMoney(int money) {
        if (money < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 최소 " + MINIMUM_BETTING_MONEY + "원부터 가능합니다.");
        }
    }
}

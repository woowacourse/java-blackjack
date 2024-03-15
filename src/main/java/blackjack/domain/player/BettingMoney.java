package blackjack.domain.player;

public record BettingMoney(int value) {
    private static final int MINIMUM_VALUE = 0;

    public BettingMoney {
        validateMoney(value);
    }

    public void validateMoney(final int value) {
        if (value <= MINIMUM_VALUE) {
            throw new IllegalArgumentException(String.format("배팅 금액은 최소%d 보다 커야 합니다.", MINIMUM_VALUE));
        }
    }
}

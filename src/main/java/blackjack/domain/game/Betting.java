package blackjack.domain.game;

public final class Betting {

    private final long money;

    public Betting(long money) {
        validatePositiveMoney(money);
        this.money = money;
    }

    private void validatePositiveMoney(long money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    public long multiply(float multiplier) {
        return (long)(money * multiplier);
    }

    public long getValue() {
        return money;
    }
}

package blackjack.domain.state;

public final class Bet {

    private final long money;

    public Bet(long money) {
        validatePositiveMoney(money);
        this.money = money;
    }

    private void validatePositiveMoney(long money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 양수여야 합니다.");
        }
    }

    long multiply(float multiplier) {
        return (long)(money * multiplier);
    }
}

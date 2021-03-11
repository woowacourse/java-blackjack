package blackjack.domain;

public class Profit {

    private static final double BLACKJACK_RATE = 1.5;
    private static final double WIN_RATE = 1.0;
    private static final double LOSE_RATE = -1.0;

    private final int money;

    public Profit(final int money) {
        validate(money);
        this.money = money;
    }

    private void validate(final int value) {
        validatePositive(value);
    }

    private void validatePositive(final int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("[에러]: 베팅 금액은 음수 또는 0이 될 수 없습니다.");
        }
    }

    public int blackjackWin() {
        return (int) (money * BLACKJACK_RATE);
    }

    public int win() {
        return (int) (money * WIN_RATE);
    }

    public int lose() {
        return (int) (money * LOSE_RATE);
    }
}

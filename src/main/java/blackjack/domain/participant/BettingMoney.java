package blackjack.domain.participant;

public class BettingMoney {

    private final int value;

    BettingMoney(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 작을 수 없습니다.");
        }
        this.value = value;
    }

    public int value() {
        return value;
    }

    int profit(final double profit) {
        return (int) (value * profit);
    }
}

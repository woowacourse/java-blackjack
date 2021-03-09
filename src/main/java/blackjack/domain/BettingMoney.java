package blackjack.domain;

public class BettingMoney {
    private static final int BETTING_BOUND = 2_000_000_000;

    public BettingMoney(int bettingMoney) {
        validate(bettingMoney);
    }

    private void validate(int bettingMoney) {
        if (bettingMoney > BETTING_BOUND) {
            throw new IllegalArgumentException("배팅 금액은 20억원을 초과할 수 없습니다.");
        }
    }
}

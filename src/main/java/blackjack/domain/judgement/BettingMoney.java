package blackjack.domain.judgement;

import java.math.BigDecimal;

public class BettingMoney {

    private final int money;

    public BettingMoney(String rawBettingMoney) {
        int money = parseInt(rawBettingMoney);
        validatePositive(money);
        this.money = money;
    }

    @Override
    public String toString() {
        return String.valueOf(money);
    }

    private int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("배팅 금액은 정수를 입력해 주세요.");
        }
    }

    private void validatePositive(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(money);
    }
}

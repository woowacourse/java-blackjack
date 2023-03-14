package blackjack.domain.betting;

public class BettingMoney {

    private final int money;

    public BettingMoney(final String money) {
        validateNullOrBlank(money);
        validateDigit(money);
        validatePositive(money);
        this.money = Integer.parseInt(money);
    }

    public int getMoney() {
        return money;
    }

    private void validateNullOrBlank(final String money) {
        if (money == null || money.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 null이나 공백이면 안됩니다.");
        }
    }

    private void validateDigit(final String moeny) {
        try {
            Integer.parseInt(moeny);
        } catch (Exception e) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 숫자여야 합니다.");
        }
    }

    private void validatePositive(final String money) {
        if (Integer.parseInt(money) <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수이어야 합니다.");
        }
    }
}

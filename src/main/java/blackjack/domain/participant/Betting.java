package blackjack.domain.participant;

public class Betting {

    private static final int BETTING_MIN_MONEY = 1_000;
    private static final int BETTING_MAX_MONEY = 100_000_000;

    private final int money;

    public Betting(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < BETTING_MIN_MONEY || money > BETTING_MAX_MONEY) {
            throw new IllegalArgumentException("배팅 금액은 최소 1천원, 최대 1억원까지 가능합니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}

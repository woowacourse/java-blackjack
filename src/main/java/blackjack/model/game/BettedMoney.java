package blackjack.model.game;

public class BettedMoney {

    public static final int MINIMUM_AVAILABLE_MONEY = 0;
    public static final int MAXIMUM_AVAILABLE_MONEY = 1_000_000_000;
    private final int money;

    public BettedMoney(final int money) {
        validate(money);
        this.money = money;
    }

    private void validate(final int money) {
        if (money < MINIMUM_AVAILABLE_MONEY) {
            throw new IllegalArgumentException(String.format("%d원 이상 베팅 가능합니다.", MINIMUM_AVAILABLE_MONEY));
        }
        if (money > MAXIMUM_AVAILABLE_MONEY) {
            throw new IllegalArgumentException("최대 10억 원까지 베팅 가능합니다.");
        }
    }

    public int getMoney() {
        return money;
    }
}

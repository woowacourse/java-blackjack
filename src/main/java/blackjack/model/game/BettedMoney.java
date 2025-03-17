package blackjack.model.game;

public class BettedMoney {

    public static final int UNIT_OF_BETTED_MONEY = 10_000;
    public static final int MAXIMUM_AVAILABLE_MONEY = 1_000_000_000;
    private final long money;

    public BettedMoney(final int money) {
        validate(money);
        this.money = money;
    }

    private void validate(final int money) {
        if (money < UNIT_OF_BETTED_MONEY) {
            throw new IllegalArgumentException(String.format("%d원 이상 베팅 가능합니다.", UNIT_OF_BETTED_MONEY));
        }
        if (money > MAXIMUM_AVAILABLE_MONEY) {
            throw new IllegalArgumentException("최대 10억 원까지 베팅 가능합니다.");
        }
        if (money % UNIT_OF_BETTED_MONEY != 0) {
            throw new IllegalArgumentException(String.format("%d원 단위로 베팅 가능합니다.", UNIT_OF_BETTED_MONEY));
        }
    }

    public long getMoney() {
        return money;
    }
}

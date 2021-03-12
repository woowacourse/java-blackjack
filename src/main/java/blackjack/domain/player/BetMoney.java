package blackjack.domain.player;

public class BetMoney {
    private final int money;
    private static final int initAmount = 0;
    private static final BetMoney initMoney;

    static {
        initMoney = new BetMoney(initAmount);
    }

    public static BetMoney getInitMoney() {
        return initMoney;
    }

    public BetMoney(final int money) {
        validateMoney(money);
        this.money = money;
    }

    private void validateMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
    }

    public int toInt() {
        return this.money;
    }
}

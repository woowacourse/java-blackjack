package blackjack.domain.money;

public class BettingMoney {

    public static final int BETTING_MONEY_MIN = 0;

    private int money;

    public BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < BETTING_MONEY_MIN) {
            throw new IllegalArgumentException(String.format("%s원 이상 배팅해주세요.", BETTING_MONEY_MIN));
        }
    }

    public int getValue() {
        return money;
    }
}

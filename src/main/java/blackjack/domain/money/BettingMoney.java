package blackjack.domain.money;

public class BettingMoney {

    private int money;

    public BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money < 0) {
            throw new IllegalArgumentException("0원 이상 배팅해주세요.");
        }
    }

    public int getValue() {
        return money;
    }
}

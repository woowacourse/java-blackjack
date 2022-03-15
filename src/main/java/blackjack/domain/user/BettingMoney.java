package blackjack.domain.user;

public class BettingMoney {
    private final int bettingMoney;

    public BettingMoney(int money) {
        validateBettingMoney(money);
        this.bettingMoney = money;
    }

    private void validateBettingMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 이하일 수 없습니다.");
        }
    }

    public int getBettingMoney() {
        return this.bettingMoney;
    }
}

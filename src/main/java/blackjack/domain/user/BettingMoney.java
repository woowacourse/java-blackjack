package blackjack.domain.user;

public class BettingMoney {
    private final int bettingMoney;

    public BettingMoney(int money) {
        validateBettingMoney(money);
        this.bettingMoney = money;
    }

    private void validateBettingMoney(int money) {
        validateNegative(money);
        validateBettingMoneyUnit(money);
    }

    private void validateBettingMoneyUnit(int money) {
        if ((money % 10) != 0) {
            throw new IllegalArgumentException("배팅 금액은 10원 단위여야 합니다.");
        }
    }

    private void validateNegative(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원 이하일 수 없습니다.");
        }
    }

    public int calculateRevenue(double earningRate) {
        return (int) (bettingMoney * earningRate);
    }
}

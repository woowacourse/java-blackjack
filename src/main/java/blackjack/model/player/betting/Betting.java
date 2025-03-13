package blackjack.model.player.betting;

public class Betting {

    private final int bettingMoney;
    private int balance;

    private Betting(final int bettingMoney, final int balance) {
        this.bettingMoney = bettingMoney;
        this.balance = balance;
    }

    public static Betting bet(final int bettingMoney) {
        return new Betting(bettingMoney, bettingMoney);
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    public void earn(final int money) {
        validateEarnMoney(money);
        balance += money;
    }

    private void validateEarnMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("음수는 얻을 수 없습니다.");
        }
    }

    public void lose() {
        balance = 0;
    }

    public int getProfit() {
        return balance - bettingMoney;
    }

    public int getBalance() {
        return balance;
    }
}

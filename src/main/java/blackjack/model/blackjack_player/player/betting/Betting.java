package blackjack.model.blackjack_player.player.betting;

public final class Betting {

    private final int bettingMoney;
    private int balance;

    private Betting(final int bettingMoney, final int balance) {
        validateNegativeMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
        this.balance = balance;
    }

    public static Betting bet(final int bettingMoney) {
        return new Betting(bettingMoney, bettingMoney);
    }

    public void earn(final int money) {
        validateNegativeMoney(money);
        balance += money;
    }

    public void lose() {
        balance = 0;
    }

    private void validateNegativeMoney(final int money) {
        if (money < 0) {
            throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
        }
    }

    public int getBalance() {
        return balance;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}

package blackjack.domain.gamer;

public class BettingMoney {

    private static final int BETTINGMONEY_THRESHOLD = 0;

    private int bettingMoney;

    private BettingMoney(final int bettingMoney) {
        if (bettingMoney <= BETTINGMONEY_THRESHOLD) {
            throw new IllegalArgumentException("배팅 금액은 음수일 수 없습니다.");
        }
        this.bettingMoney = bettingMoney;
    }

    public static BettingMoney from(String money) {
        return new BettingMoney(Integer.parseInt(money));
    }

    public static BettingMoney from(int money) {
        return new BettingMoney(money);
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}

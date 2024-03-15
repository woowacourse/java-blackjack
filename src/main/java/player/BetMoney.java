package player;

public class BetMoney {

    private int betAmount;

    public BetMoney(int money) {
        validateCanBetMoney(money);
        this.betAmount = money;
    }

    private void validateCanBetMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0원이거나 음수일 수 없습니다.");
        }
    }
}

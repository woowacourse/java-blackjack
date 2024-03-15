package player;

public class Money {

    private int betAmount;

    public Money(int money) {
        validateCanBetMoney(money);
        this.betAmount = money;
    }

    private void validateCanBetMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0원이거나 음수일 수 없습니다.");
        }
    }
}

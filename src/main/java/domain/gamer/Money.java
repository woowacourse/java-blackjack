package domain.gamer;

public class Money {
    private final int money;

    public Money(int money) {
        validateMoneyRange(money);
        this.money = money;
    }

    private void validateMoneyRange(int money) {
        if (money <= 0 || money > 100_000_000) {
            throw new IllegalArgumentException("배팅 최소금액은 0원, 최대 금액은 1억원 입니다.");
        }
    }

    public int getResultMoneyValue(PlayerResult playerResult) {
        double ratio = playerResult.getRatio();
        return (int) (ratio * money);
    }
}

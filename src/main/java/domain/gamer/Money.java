package domain.gamer;

public class Money {
    private final int money;

    public Money(int money) {
        validateMoneyRange(money);
        this.money = money;
    }

    private void validateMoneyRange(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("돈은 0보다 큰 자연수여야 합니다.");
        }
    }

    public int getResultMoney(PlayerResult playerResult) {
        double ratio = playerResult.getRatio();
        return (int) (ratio * money);
    }
}

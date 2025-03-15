package domain.game;

public class BetMoney {
    private final int amount;

    public BetMoney(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 0 이나 음수가 될 수 없습니다");
        }
        if (amount > 100_000_000) {
            throw new IllegalArgumentException("배팅 금액은 1억원을 넘을 수 없습니다.");
        }
    }
}

package domain.gamer;

public class Betting {
    private static final int MINIMUM_AMOUNT = 1000;
    private final Integer amount;

    public Betting(Integer amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(Integer amount) {
        if (amount == null || amount < MINIMUM_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅은 최소 1000원 이상이어야합니다.");
        }
    }

    public Integer getAmount() {
        return amount;
    }
}

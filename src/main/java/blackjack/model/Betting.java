package blackjack.model;

public class Betting {

    private final int amount;

    public Betting(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if ((amount * 1.5) % 10 != 0) {
            throw new IllegalStateException("유효하지 않은 금액입니다.");
        }
    }

    public int getAmount() {
        return amount;
    }
}

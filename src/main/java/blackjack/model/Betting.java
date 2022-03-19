package blackjack.model;

public class Betting {

    private final int amount;

    public Betting(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (Profit.isDivisibleByTen(amount)) {
            throw new IllegalStateException("유효하지 않은 금액입니다.");
        }
    }

    public int calculateResult(Result result, boolean isBlackjackWin) {
        if (isBlackjackWin) {
            return (int) (amount * Profit.BLACKJACK_WIN_PROFIT_RATE);
        }
        return amount * result.getProfitRate();
    }

    public int getAmount() {
        return amount;
    }
}

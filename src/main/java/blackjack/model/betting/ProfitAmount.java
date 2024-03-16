package blackjack.model.betting;

public class ProfitAmount {

    private final int profitAmount;

    public ProfitAmount(int profitAmount) {
        validatePositive(profitAmount);
        this.profitAmount = profitAmount;
    }

    private void validatePositive(int profitAmount) {
        if (profitAmount < 0) {
            String errorMessage = String.format("[ERROR] 수익은 음수일 수 없습니다.(등록 수익 : %d", profitAmount);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public int getProfitAmount() {
        return profitAmount;
    }
}

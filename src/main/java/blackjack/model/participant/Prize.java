package blackjack.model.participant;

public class Prize {

    private static final int NEGATIVE_MULTIPLIER = -1;
    private static final float BLACKJACK_PROFIT = 1.5f;

    private final int amount;
    private final boolean isBlackjack;

    private Prize(int amount, boolean isBlackjack) {
        this.amount = amount;
        this.isBlackjack = isBlackjack;
    }

    public static Prize of(int betAmount) {
        if (betAmount <= 0 || betAmount % 100 != 0) {
            throw new IllegalArgumentException("100원 단위의 자연수가 아닙니다.");
        }

        return new Prize(betAmount, false);
    }

    public Prize lose() {
        validateAmount();
        return new Prize(amount * NEGATIVE_MULTIPLIER, false);
    }

    public Prize blackjack() {
        validateAmount();
        return new Prize((int) (amount * BLACKJACK_PROFIT), true);
    }

    public int getAmount() {
        return amount;
    }

    private void validateAmount() {
        if (amount < 0) {
            throw new IllegalStateException("이미 패배 처리되었습니다.");
        }

        if (isBlackjack) {
            throw new IllegalStateException("이미 블랙잭 처리됐습니다.");
        }
    }
}

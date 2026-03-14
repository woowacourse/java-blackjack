package domain.bet;

public record Betting(
        int amount
) {
    private static final int UNIT = 1000;

    public Betting {
        validateAmount(amount);
    }

    private void validateAmount(int amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("돈은 %d원 단위로 입력해 주세요.".formatted(UNIT));
        }
    }
}

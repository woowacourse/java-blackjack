package domain.bet;

public record Betting(
        Integer amount
) {
    private static final int UNIT = 1000;

    public Betting {
        validateNull(amount);
        validateAmount(amount);
    }

    private void validateNull(Integer amount) {
        if (amount == null) {
            throw new IllegalArgumentException("Betting 금액은 null값일 수 없습니다.");
        }
    }

    private void validateAmount(Integer amount) {
        if (amount % UNIT != 0) {
            throw new IllegalArgumentException("돈은 %d원 단위로 입력해 주세요.".formatted(UNIT));
        }
    }
}

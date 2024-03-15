package domain.participant.attributes;

public record Bet(int amount) implements Money {

    private static final int MIN_BET_AMOUNT = 1000;

    public Bet {
        validateMinAmount(amount);
        validateDivisionByMinAmount(amount);
    }

    private void validateMinAmount(final int amount) {
        if (amount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 최소 %d입니다: %d".formatted(MIN_BET_AMOUNT, amount));
        }
    }

    private void validateDivisionByMinAmount(final int amount) {
        if ((amount % MIN_BET_AMOUNT) != 0) {
            throw new IllegalArgumentException("%d원 단위로 입력해주세요: %d".formatted(MIN_BET_AMOUNT, amount));
        }
    }
}

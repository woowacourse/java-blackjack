package blackjack.domain.round;

public record Bet(
        int value
) {

    private static final int INCREMENT = 1_000;
    private static final int MAX_BET = 100_000_000;

    public Bet {
        validate(value);
    }

    private void validate(int value) {
        if (value < INCREMENT) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 이상의 수를 입력해 주세요.", INCREMENT));
        }
        if (value % INCREMENT != 0) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 단위로 입력해 주세요.", INCREMENT));
        }
        if (value > MAX_BET) {
            throw new IllegalArgumentException(String.format("[ERROR] %s 이하의 수를 입력해 주세요.", MAX_BET));
        }
    }
}

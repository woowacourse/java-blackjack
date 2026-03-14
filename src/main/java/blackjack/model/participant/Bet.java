package blackjack.model.participant;

public record Bet(
        int amount
) {

    private static final int DIVISOR = 100;

    public Bet {
        validateAmount(amount);
        validateDivisible(amount);
    }

    public static Bet from(String rawAmount) {
        validateDigit(rawAmount);
        int amount = Integer.parseInt(rawAmount);

        return new Bet(amount);
    }

    private static void validateDigit(String rawAmount) {
        boolean isDigit = rawAmount.chars()
                .allMatch(Character::isDigit);

        if (!isDigit) {
            throw new IllegalArgumentException("베팅 금액은 숫자여야 합니다.");
        }
    }

    private void validateDivisible(int amount) {
        if (amount % DIVISOR != 0) {
            throw new IllegalArgumentException("베팅 금액은 " + DIVISOR + "로 나누어 떨어져야 합니다.");
        }
    }

    private void validateAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }
}

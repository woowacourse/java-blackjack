package blackjack.domain.player;

public final class BetAmount {

    public static final int MIN_BET_AMOUNT = 1000;

    public final int amount;

    private BetAmount(int amount) {
        this.amount = amount;
    }

    public static BetAmount from(final String amount) {
        validateNumber(amount);
        validatePositiveNumber(Integer.parseInt(amount));
        return new BetAmount(Integer.parseInt(amount));
    }

    private static void validateNumber(final String amount) {
        try {
            Integer.parseInt(amount);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("베팅 금액은 숫자만 입력할 수 있습니다.");
        }
    }

    private static void validatePositiveNumber(final int amount) {
        if (amount < MIN_BET_AMOUNT) {
            throw new IllegalArgumentException("베팅 최소 금액은 1000원 입니다.");
        }
    }

    public int getValue() {
        return amount;
    }
}

package domain;

public class Betting {
    public static final String ERROR_BET_MUST_BE_POSITIVE = "[ERROR] 베팅 금액은 0보다 커야 합니다.";
    public static final String ERROR_BET_MONEY_SCALE = "[ERROR] 돈의 단위를 잘못 입력하였습니다.";
    public static final int INITIAL_AMOUNT = 0;
    public static final int BET_UNIT = 10000;
    public static final int BLACKJACK_NUMERATOR = 3;
    public static final int BLACKJACK_DENOMINATOR = 2;
    public static final int ZERO = 0;
    private final int amount;

    private Betting(int amount) {
        this.amount = amount;
    }

    public static Betting none() {
        return new Betting(INITIAL_AMOUNT);
    }

    public static Betting of(int amount) {
        validateBettingAmount(amount);
        return new Betting(amount);
    }

    private static void validateBettingAmount(int amount) {
        if (amount <= INITIAL_AMOUNT) {
            throw new IllegalArgumentException(ERROR_BET_MUST_BE_POSITIVE);
        }
        if (amount % BET_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_BET_MONEY_SCALE);
        }
    }

    public int profit(WinningStatus status) {
        return switch (status) {
            case BLACKJACK_WIN -> amount * BLACKJACK_NUMERATOR / BLACKJACK_DENOMINATOR;
            case WIN -> amount;
            case TIE -> ZERO;
            case LOSE -> -amount;
        };
    }
}

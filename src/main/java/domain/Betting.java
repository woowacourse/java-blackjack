package domain;

public class Betting {
    public static final String ERROR_BET_MUST_BE_POSITIVE = "[ERROR] 베팅 금액은 0보다 커야 합니다.";
    public static final String ERROR_BET_MONEY_SCALE = "[ERROR] 돈의 단위를 잘못 입력하였습니다.";
    public static final double SPECIAL_SCALE = 1.5;
    public static final int INITIAL_AMOUNT = 0;
    public static final int BET_UNIT = 10000;
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

    public int amount() {
        return amount;
    }

    public int settle(WinningStatus status) {
        return (int) switch (status) {
            case BLACKJACK_WIN -> amount * SPECIAL_SCALE + amount;
            case WIN -> amount + amount;
            case TIE -> amount;
            case LOSE -> INITIAL_AMOUNT;
        };
    }
}

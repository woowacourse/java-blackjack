package domain.result;

public enum Result {
    WIN(),
    LOSE(),
    TIE();

    public static Result isHigherPlayerHandValue(int playerHandValue, int dealerHandValue) {
        if (playerHandValue > dealerHandValue) {
            return WIN;
        }
        return LOSE;
    }

    public static Result isGreaterPlayerHandCount(int playerHandCount, int dealerHandCount) {
        if (playerHandCount == dealerHandCount) {
            return TIE;
        }
        if (playerHandCount > dealerHandCount) {
            return LOSE;
        }
        return WIN;
    }
}

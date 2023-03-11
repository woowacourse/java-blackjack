package domain;

public class BetAmount {
    public BetAmount(int betAmount) {
        if (betAmount < 1000) {
            throw new IllegalArgumentException(ExceptionCode.LEAK_BET_AMOUNT.getExceptionCode());
        }
    }
}

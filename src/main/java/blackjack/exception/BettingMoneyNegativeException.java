package blackjack.exception;

public class BettingMoneyNegativeException extends IllegalArgumentException {
    public BettingMoneyNegativeException(String message) {
        super(message);
    }
}

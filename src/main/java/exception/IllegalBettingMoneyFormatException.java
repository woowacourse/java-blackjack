package exception;

public class IllegalBettingMoneyFormatException extends RuntimeException{
    public IllegalBettingMoneyFormatException(String message) {
        super(message);
    }
}
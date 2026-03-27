package exception;

public class InvalidBettingAmountFormatException extends BlackjackException{
    public InvalidBettingAmountFormatException() {
        super("숫자를 입력해야 합니다.");
    }
}

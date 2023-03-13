package blackjack.exception;

public class InvalidChoiceException extends CustomException {

    private static final String MESSAGE = "올바르지 않은 입력입니다. y 또는 n 중에 골라주세요.";

    public InvalidChoiceException() {
        super(MESSAGE);
    }
}

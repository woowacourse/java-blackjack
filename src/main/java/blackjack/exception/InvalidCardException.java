package blackjack.exception;

public class InvalidCardException extends RuntimeException {

    private static final String MESSAGE = "[ERROR] 존재하지 않는 카드 형식 입니다.";

    public InvalidCardException() {
        super(MESSAGE);
    }
}

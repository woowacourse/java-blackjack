package domain.state.exceptions;

public class CardNotExistException extends RuntimeException {

    private static final String MESSAGE = "카드가 없습니다.";

    public CardNotExistException() {
        super(MESSAGE);
    }
}

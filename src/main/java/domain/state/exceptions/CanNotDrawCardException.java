package domain.state.exceptions;

public class CanNotDrawCardException extends RuntimeException {

    private static final String MESSAGE = "카드를 뽑을 수 없는 상태";

    public CanNotDrawCardException() {
        super(MESSAGE);
    }
}

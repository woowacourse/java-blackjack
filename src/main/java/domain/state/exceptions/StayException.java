package domain.state.exceptions;

public class StayException extends RuntimeException {

    private static final String MESSAGE = "게임을 멈출 수 없는 상태입니다.";

    public StayException() {
        super(MESSAGE);
    }
}

package domain.exception;

public class OutOfBoundException extends RuntimeException {

    public OutOfBoundException() {
        super("값의 범위가 1미만 혹은 10초과입니다.");
    }
}

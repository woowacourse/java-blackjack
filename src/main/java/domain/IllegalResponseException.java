package domain;

public class IllegalResponseException extends IllegalArgumentException {
    public IllegalResponseException() {
        super("값이 올바르지 않습니다.");
    }
}

package domain;

public class IllegalResponseException extends IllegalArgumentException {
    public IllegalResponseException(String input) {
        super(String.format("값이 올바르지 않습니다. (입력 : %s)", input));
    }
}

package exception;

public class IllegalToStayInReadyException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 게임 준비 단계에는 상태를 조작할 수 없습니다.";

    public IllegalToStayInReadyException() {
        super(MESSAGE);
    }
}

package exception;

public class IllegalToStayInFinishedException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 게임이 종료되어 상태를 조작할 수 없습니다.";

    public IllegalToStayInFinishedException() {
        super(MESSAGE);
    }
}

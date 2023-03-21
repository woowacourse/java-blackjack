package exception;

public class IllegalDrawCommandException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 카드 드로우 커맨드는 y,n 둘 중 하나입니다.";

    public IllegalDrawCommandException() {
        super(MESSAGE);
    }
}

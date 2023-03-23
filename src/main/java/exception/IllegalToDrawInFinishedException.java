package exception;

public class IllegalToDrawInFinishedException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 게임이 종료되어 카드를 뽑을 수 없습니다.";

    public IllegalToDrawInFinishedException() {
        super(MESSAGE);
    }
}

package exception;

public class ThereIsNoPlayerUnfinishedException extends RuntimeException {

    public static final String MESSAGE = "[ERROR] 게임이 미완료된 플레이어가 없습니다.";

    public ThereIsNoPlayerUnfinishedException() {
        super(MESSAGE);
    }
}

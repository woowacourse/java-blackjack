package blakcjack.exception;

public class GameTerminationException extends RuntimeException {
    public GameTerminationException() {
        super("게임을 종료합니다.");
    }
}

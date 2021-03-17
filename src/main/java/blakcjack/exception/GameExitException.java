package blakcjack.exception;

public class GameExitException extends RuntimeException {
    public GameExitException() {
        super("게임을 종료합니다.");
    }
}

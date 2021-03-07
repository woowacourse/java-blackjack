package blakcjack.exception;

public class FailedCacheHitException extends RuntimeException {
    public FailedCacheHitException() {
        super("캐시에 없습니다.");
    }
}

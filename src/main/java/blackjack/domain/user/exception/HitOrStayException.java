package blackjack.domain.user.exception;

public class HitOrStayException extends RuntimeException {
    public HitOrStayException() {
        super("유효하지않는 입력입니다.");
    }
}

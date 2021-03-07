package blakcjack.view.reply;

public class InvalidReplyException extends RuntimeException {
    public InvalidReplyException() {
        super("적절한 응답이 아닙니다.");
    }
}

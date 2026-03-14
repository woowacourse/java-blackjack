package constant.exception;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException() {
        super("카드가 중복 되었습니다.");
    }
}

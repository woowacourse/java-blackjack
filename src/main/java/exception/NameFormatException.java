package exception;

public class NameFormatException extends RuntimeException {
    public NameFormatException(String name) {
        super("잘못된 이름입니다. " + name);
    }
}

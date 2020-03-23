package exception;

public class NameFormatException extends RuntimeException {
	public NameFormatException(String name) {
		super(String.format("잘못된 이름입니다. %s", name));
	}
}

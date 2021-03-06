package blakcjack.domain.name;

public class IllegalPlayerNameException extends RuntimeException {
	public static final String ILLEGAL_NAME_ERROR = "타당한 이름이 아닙니다.";

	public IllegalPlayerNameException(final String message) {
		super(message);
	}
}

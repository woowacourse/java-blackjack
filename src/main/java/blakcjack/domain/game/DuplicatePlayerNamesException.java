package blakcjack.domain.game;

public class DuplicatePlayerNamesException extends RuntimeException {
	public static final String DUPLICATE_NAME_ERROR = "중복된 이름이 입력 되었습니다.";

	public DuplicatePlayerNamesException(final String message) {
		super(message);
	}
}

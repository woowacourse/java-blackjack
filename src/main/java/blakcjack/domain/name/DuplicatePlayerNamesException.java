package blakcjack.domain.name;

public class DuplicatePlayerNamesException extends RuntimeException {
	public static final String DUPLICATE_NAME_ERROR = "중복된 이름이 입력 되었습니다.";

	public DuplicatePlayerNamesException() {
		super(DUPLICATE_NAME_ERROR);
	}
}

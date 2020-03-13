package domains.user;

public class InvalidPlayerException extends IllegalArgumentException {
	public static final String INVALID_INPUT = "y 혹은 n으로 입력해주세요.";
	static final String NULL_OR_EMPTY = "빈 문자열이나 null은 입력할 수 없습니다.";

	InvalidPlayerException(String s) {
		super(s);
	}
}

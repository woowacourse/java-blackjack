package domains.user.name;

public class InvalidPlayerNameException extends IllegalArgumentException {
	public static final String NULL_OR_EMPTY = "이름은 빈 문자열이나 null이 될 수 없습니다.";
	public static final String SAME_AS_DEALER = "이름은 딜러와 같을 수 없습니다.";

	InvalidPlayerNameException(String s) {
		super(s);
	}
}

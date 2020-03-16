package blackjack.domain.exceptions;

public class InvalidDrawOpinionException extends IllegalArgumentException {
	public static final String INVALID = "y 또는 n만 입력 가능합니다.";

	public InvalidDrawOpinionException(String s) {
		super(s);
	}
}

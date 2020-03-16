package blackjack.domain.exceptions;

public class InvalidChoiceException extends IllegalArgumentException {
	public static final String INVALID = "y 또는 n만 입력 가능합니다.";

	public InvalidChoiceException(String s) {
		super(s);
	}
}

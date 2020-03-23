package blackjack.domain.exceptions;

public class InvalidHandTypeException extends IllegalArgumentException {
	public static final String NULL = "점수가 존재하지 않습니다.";

	public InvalidHandTypeException(String s) {
		super(s);
	}
}

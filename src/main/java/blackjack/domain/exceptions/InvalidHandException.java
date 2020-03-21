package blackjack.domain.exceptions;

public class InvalidHandException extends IllegalArgumentException {
	public static final String NULL = "추가할 카드가 존재하지 않습니다.";
	public static final String EMPTY = "추가할 카드들이 존재하지 않습니다.";

	public InvalidHandException(String s) {
		super(s);
	}
}

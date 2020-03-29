package blackjack.domain.exceptions;

public class InvalidCardException extends IllegalArgumentException {
	public static final String NULL = "심볼이나 타입이 존재하지 않습니다.";

	public InvalidCardException(String s) {
		super(s);
	}
}

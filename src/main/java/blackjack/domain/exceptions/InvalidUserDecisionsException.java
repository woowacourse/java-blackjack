package blackjack.domain.exceptions;

public class InvalidUserDecisionsException extends IllegalArgumentException {
	public static final String NULL = "choice 또는 handStatus 또는 dealerChoice가 입력되지 않았습니다.";

	public InvalidUserDecisionsException(String s) {
		super(s);
	}
}

package blackjack.domain.exceptions;

public class InvalidResultScoreException extends IllegalArgumentException {
	public static final String NULL = "점수 또는 점수 타입이 존재하지 않습니다.";

	public InvalidResultScoreException(String s) {
		super(s);
	}
}

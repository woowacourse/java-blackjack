package blackjack.domain.exceptions;

public class InvalidResultTypeException extends IllegalArgumentException {
	public static final String NULL = "플레이어 또는 딜러의 점수 결과가 존재하지 않습니다.";

	public InvalidResultTypeException(String s) {
		super(s);
	}
}

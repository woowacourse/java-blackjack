package blackjack.domain.exceptions;

public class InvalidScoreException extends IllegalArgumentException {
	public static final String INVALID = "점수는 0보다 작을 수 없습니다.";
	public static final String NULL = "점수를 계산할 카드가 존재하지 않습니다.";

	public InvalidScoreException(String s) {
		super(s);
	}
}

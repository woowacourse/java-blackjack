package blackjack.domain.user.hand;

public class InvalidScoreException extends IllegalArgumentException {
	public static final String INVALID = "점수는 0보다 작을 수 없습니다.";

	public InvalidScoreException(String s) {
		super(s);
	}
}

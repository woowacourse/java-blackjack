package blackjack.domain.exceptions;

public class InvalidUserException extends IllegalArgumentException {
	public static final String NULL_NAME = "유저의 이름이 존재하지 않습니다.";
	public static final String NULL_HAND = "유저의 패가 존재하지 않습니다.";
	public static final String INVALID_DRAW_NUMBER = "뽑을 수 있는 카드 수가 유효하지 않습니다.";

	public InvalidUserException(String s) {
		super(s);
	}
}

package blackjack.domain.card;

public class InvalidDeckException extends IllegalArgumentException {
	public static final String INVALID = "카드 덱에 중복이 존재합니다.";

	public InvalidDeckException(String s) {
		super(s);
	}
}

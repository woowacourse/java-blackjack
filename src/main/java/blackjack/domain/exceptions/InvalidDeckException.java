package blackjack.domain.exceptions;

public class InvalidDeckException extends IllegalArgumentException {
	public static final String EMPTY = "덱이 존재하지 않습니다.";
	public static final String DUPLICATE = "카드 덱에 중복이 존재합니다.";
	public static final String DECK_EMPTY = "덱의 모든 카드를 소진하였습니다.";

	public InvalidDeckException(String s) {
		super(s);
	}
}

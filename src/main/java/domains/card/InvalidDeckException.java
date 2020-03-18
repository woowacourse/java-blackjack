package domains.card;

public class InvalidDeckException extends IllegalArgumentException {

	public static final String DECK_IS_EMPTY = "덱이 갖고 있는 카드가 0장입니다.";

	InvalidDeckException(String s) {
		super(s);
	}
}

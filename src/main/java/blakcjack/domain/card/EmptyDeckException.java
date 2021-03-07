package blakcjack.domain.card;

public class EmptyDeckException extends RuntimeException {
	public static final String EMPTY_DECK_ERROR = "덱에 카드가 없습니다.";

	public EmptyDeckException() {
		super(EMPTY_DECK_ERROR);
	}
}

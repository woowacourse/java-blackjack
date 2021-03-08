package blakcjack.domain.card;

public class NoSuchCardException extends RuntimeException {
	public static final String NO_SUCH_CARD_ERROR = "해당 카드는 존재하지 않는 카드입니다.";

	public NoSuchCardException() {
		super(NO_SUCH_CARD_ERROR);
	}
}

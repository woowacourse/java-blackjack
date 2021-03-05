package blakcjack.domain.card;

public class emptyDeckException extends RuntimeException {
	public emptyDeckException(final String message) {
		super(message);
	}
}

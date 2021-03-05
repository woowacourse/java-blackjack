package blakcjack.domain.card;

public class EmptyDeckException extends RuntimeException {
	public EmptyDeckException(final String message) {
		super(message);
	}
}

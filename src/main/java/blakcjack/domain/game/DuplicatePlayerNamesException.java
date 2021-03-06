package blakcjack.domain.game;

public class DuplicatePlayerNamesException extends RuntimeException {
	public DuplicatePlayerNamesException(final String message) {
		super(message);
	}
}

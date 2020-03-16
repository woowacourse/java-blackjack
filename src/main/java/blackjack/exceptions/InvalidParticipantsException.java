package blackjack.exceptions;

public class InvalidParticipantsException extends IllegalArgumentException {
    public InvalidParticipantsException(final String message) {
        super(message);
    }
}

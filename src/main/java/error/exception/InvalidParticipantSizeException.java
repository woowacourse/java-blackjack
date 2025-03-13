package error.exception;

import static error.ErrorMessage.INVALID_PARTICIPANT_SIZE;

public class InvalidParticipantSizeException extends RuntimeException {

    public InvalidParticipantSizeException(int size) {
        super(INVALID_PARTICIPANT_SIZE.getMessage().formatted(size));
    }
}

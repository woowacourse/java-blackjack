package exception;

import constants.ErrorCode;

public class InvalidInputException extends CustomException {

    public InvalidInputException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

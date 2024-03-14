package exception;

import constants.ErrorCode;

public class InvalidPlayerNameException extends CustomException {

    public InvalidPlayerNameException(final ErrorCode errorCode) {
        super(errorCode);
    }
}

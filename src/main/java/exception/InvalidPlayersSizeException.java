package exception;

import constants.ErrorCode;

public class InvalidPlayersSizeException extends CustomException {

    public InvalidPlayersSizeException(final ErrorCode errorCode) {
        super(errorCode);
    }
}
